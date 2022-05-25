package com.mvpMatch.vendingmachineservice.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.mvpMatch.vendingmachineservice.model.Product
import com.mvpMatch.vendingmachineservice.model.User
import com.mvpMatch.vendingmachineservice.model.dtos.JwtTokenDto
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.web.client.RestTemplate

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductControllerTest{
    @Value("\${local.server.port}")
    val port :Int? =2000
    val rest =  RestTemplate()
    val mapper = ObjectMapper()

    @Test
    fun `test create product endpoint`(){
        val token = getAuthenticatedUserToken("daniel","password","seller")
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        headers["Authorization"] = "Bearer $token"
        /**
         * {
            "productName":"food_4",
            "cost":20,
            "amountAvailable":10
            }
         */
        val requestBody = mapper.createObjectNode()
            .putPOJO("productName","Chicken")
            .putPOJO("cost",20)
            .putPOJO("amountAvailable",10)

        val url = "http://localhost:${port}/api/v1/product"
        println(url)
        val product = rest
            .postForObject(url,HttpEntity(requestBody, headers), Product::class.java,HashMap<String,Any>())

        println("product name  ==>${product?.productName}")
        println("amount   ==>${product?.amountAvailable}")
        println("cost   ==>${product?.cost}")
        println("seller name   ==>${product?.seller?.username}")

        assertEquals(10, product?.amountAvailable)
        assertEquals(20, product?.cost)
        assertEquals("Chicken", product?.productName)
        assertEquals("daniel", product?.seller?.username)

    }
    private fun getAuthenticatedUserToken(username:String,password:String,role:String): String{
        //register a user
        var headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        var req = mapper.createObjectNode()
            .putPOJO("username",username)
            .putPOJO("password", password)
            .putPOJO("role",role)
        var url = "http://localhost:${port}/api/v1/user"
        println(url)
        val user = rest
            .postForObject(url, HttpEntity(req, headers), User::class.java,HashMap<String,Any>())

        println("username ==>${user?.username}")
        println("role ==>${user?.role}")
        println("deposit ==>${user?.deposit}")


        headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        req = mapper.createObjectNode()
            .putPOJO("username",username)
            .putPOJO("password", password)

        url = "http://localhost:${port}/api/v1/oauth/token"
        println(url)
        val jwtTokenDto = rest
            .postForObject(url, HttpEntity(req, headers), JwtTokenDto::class.java,HashMap<String,Any>())

        println("accessToken ==>${jwtTokenDto?.access_token}")
        assertNotNull(jwtTokenDto?.access_token)
        return jwtTokenDto?.access_token!!
    }
}