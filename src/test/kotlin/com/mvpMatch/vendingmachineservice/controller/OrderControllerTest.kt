package com.mvpMatch.vendingmachineservice.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.mvpMatch.vendingmachineservice.model.Product
import com.mvpMatch.vendingmachineservice.model.User
import com.mvpMatch.vendingmachineservice.model.dtos.*
import com.mvpMatch.vendingmachineservice.service.CoinFrequencyService
import com.mvpMatch.vendingmachineservice.service.ProductService
import com.mvpMatch.vendingmachineservice.service.UserService
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.web.client.RestTemplate
import java.time.temporal.TemporalAmount

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderControllerTest{
    @Value("\${local.server.port}")
    val port :Int? =2000
    val rest =  RestTemplate()
    val mapper = ObjectMapper()

    @Autowired
    private val productService :ProductService?=null

    @Autowired
    private val userService :UserService?=null

    @Autowired
    private val coinFrequencyService :CoinFrequencyService?=null

    lateinit var product: Product
    lateinit var buyer:User

    @BeforeEach
    internal fun setUp() {
        //register a seller
        val seller = registerUser("Harry","harry@pass","seller")
        // seller creates product
        val product = createProduct("Pepsi",30, 10,seller)
        // register a buyer
        val buyer = registerUser("daniel","secret","buyer")
        //buyer deposit money
        deposit(100,buyer)
        this.product=product
        this.buyer=buyer
    }

    @Test
    fun `test order buy endpoint`(){
        val token = getAuthenticatedUserToken(buyer.username,"secret",buyer.role)
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        headers["Authorization"] = "Bearer $token"
        val requestBody = mapper.createObjectNode()
            .putPOJO("productId",product.id)
            .putPOJO("amountOfProduct",3)

        val url = "http://localhost:${port}/api/v1/order/buy"
        println(url)
        val orderResponse = rest
            .postForObject(url,HttpEntity(requestBody, headers), OrderResponseDto::class.java,HashMap<String,Any>())
        println("total cost ${orderResponse?.totalCost}")
        println("product name ${orderResponse?.product}")
        println("change in array format${orderResponse?.change}")
        println("change ${orderResponse?.change?.sum()}")

        val coins = coinFrequencyService?.getCoinsLessThanOrEquals(100)
        val coin10 = coins?.filter { coinFrequency -> coinFrequency.value==10  }
        val coin100 = coins?.filter { coinFrequency -> coinFrequency.value==100  }

        assertEquals(3*product.cost, orderResponse?.totalCost)
        assertEquals(product.productName, orderResponse?.product)
        assertEquals(10, orderResponse?.change?.sum())

        //expect the 10 cent coin to decrease by one due to users change deduction
        assertEquals(9, coin10?.get(0)?.frequency)
        //expect the 100 cent coin to increase by one due to users deposit action
        assertEquals(11, coin100?.get(0)?.frequency)

    }

    private fun deposit(amount: Int, buyer : User){
        val depositDto = DepositDto()
        depositDto.amount=amount
        depositDto.setPrincipalUser(buyer)
        val user = userService?.deposit(depositDto);
        println("user ${user?.username} successfully deposited ${user?.deposit}")
    }
    private fun registerUser(username:String,password:String,role:String):User{
        val userRegistrationDto = UserRegistrationDto()
        userRegistrationDto.role=role
        userRegistrationDto.username=username
        userRegistrationDto.password=password
        val  user = userService?.create(userRegistrationDto)
        return user!!
    }
    private fun createProduct(productName:String,cost:Int,amount: Int,seller : User):Product{
        val productDto = ProductDto()
        productDto.productName=productName
        productDto.cost=cost;
        productDto.amountAvailable=amount
        productDto.setPrincipalUser(seller)

        val product = productService?.create(productDto)
        println("product name ${product?.productName}")
        return product!!
    }
    private fun getAuthenticatedUserToken(username:String,password:String,role:String): String{
        //register a user
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        val req = mapper.createObjectNode()
            .putPOJO("username",username)
            .putPOJO("password", password)

        val url = "http://localhost:${port}/api/v1/oauth/token"
        println(url)
        val jwtTokenDto = rest
            .postForObject(url, HttpEntity(req, headers), JwtTokenDto::class.java,HashMap<String,Any>())

        println("accessToken ==>${jwtTokenDto?.access_token}")
        assertNotNull(jwtTokenDto?.access_token)
        return jwtTokenDto?.access_token!!
    }
}