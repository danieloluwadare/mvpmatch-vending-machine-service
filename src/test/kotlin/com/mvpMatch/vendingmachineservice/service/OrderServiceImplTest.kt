package com.mvpMatch.vendingmachineservice.service

import com.mvpMatch.vendingmachineservice.enums.OrderStatus
import com.mvpMatch.vendingmachineservice.exceptions.OrderException
import com.mvpMatch.vendingmachineservice.exceptions.UserDepositException
import com.mvpMatch.vendingmachineservice.model.CoinFrequency
import com.mvpMatch.vendingmachineservice.model.Order
import com.mvpMatch.vendingmachineservice.model.Product
import com.mvpMatch.vendingmachineservice.model.User
import com.mvpMatch.vendingmachineservice.model.dtos.OrderDto
import com.mvpMatch.vendingmachineservice.repository.OrderRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.context.ApplicationEventPublisher
import java.util.stream.Collectors

class OrderServiceImplTest{

    @Test
    fun `throw Order exception when product amount is lower than the order made`(){
        val user = User()
        user.id = 1
        user.deposit= 0

        val orderDto = OrderDto()
        orderDto.amountOfProduct=10;
        orderDto.productId=1
        orderDto.setPrincipalUser(user)

        val product = Product()
        product.amountAvailable= 5
        product.id=1

        val productService = mockk<ProductService>{
            every { findById(any()) } returns product
        }
        val coinFrequencyService = mockk<CoinFrequencyService>(relaxed = true)
        val orderRepository = mockk<OrderRepository>{
            every { save(any()) } returns Order()
        }
        val applicationEventPublisher = mockk<ApplicationEventPublisher>(){
            every { publishEvent(any()) } returns Unit
        }
        val orderServiceImpl = OrderServiceImpl(productService=productService,coinFrequencyService=coinFrequencyService,orderRepository=orderRepository,applicationEventPublisher=applicationEventPublisher)

        var exceptionThrown: Boolean = false
        var exception : Exception? = null
        try{
            orderServiceImpl.create(orderDto)
        }catch (ex: Exception){
            exception = ex
            exceptionThrown=true
            print("exception occurred")
        }

        assertTrue(exceptionThrown)
        assertInstanceOf(OrderException::class.java, exception)
    }

    @Test
    fun `throw Order exception when buyer does not have sufficient fund`(){
        val user = User()
        user.id = 1
        user.deposit= 10

        val orderDto = OrderDto()
        orderDto.amountOfProduct=10;
        orderDto.productId=1
        orderDto.setPrincipalUser(user)

        val product = Product()
        product.amountAvailable= 5
        product.id=1
        product.cost=50

        val productService = mockk<ProductService>{
            every { findById(any()) } returns product
        }
        val coinFrequencyService = mockk<CoinFrequencyService>(relaxed = true)
        val orderRepository = mockk<OrderRepository>{
            every { save(any()) } returns Order()
        }
        val applicationEventPublisher = mockk<ApplicationEventPublisher>(){
            every { publishEvent(any()) } returns Unit
        }
        val orderServiceImpl = OrderServiceImpl(productService=productService,coinFrequencyService=coinFrequencyService,orderRepository=orderRepository,applicationEventPublisher=applicationEventPublisher)

        var exceptionThrown: Boolean = false
        var exception : Exception? = null
        try{
            orderServiceImpl.create(orderDto)
        }catch (ex: Exception){
            exception = ex
            exceptionThrown=true
            print("exception occurred")
        }

        assertTrue(exceptionThrown)
        assertInstanceOf(OrderException::class.java, exception)
    }
    @Test
    fun `throw Order exception when change is unavailable`(){
        val user = User()
        user.id = 1
        user.deposit= 100

        val orderDto = OrderDto()
        orderDto.amountOfProduct=2;
        orderDto.productId=1
        orderDto.setPrincipalUser(user)

        val product = Product()
        product.amountAvailable= 5
        product.id=1
        product.cost=10

        val productService = mockk<ProductService>{
            every { findById(any()) } returns product
        }
        val coinFrequencyService = mockk<CoinFrequencyService>(relaxed = true)
        val orderRepository = mockk<OrderRepository>{
            every { save(any()) } returns Order()
        }
        val applicationEventPublisher = mockk<ApplicationEventPublisher>(){
            every { publishEvent(any()) } returns Unit
        }
        val orderServiceImpl = OrderServiceImpl(productService=productService,coinFrequencyService=coinFrequencyService,orderRepository=orderRepository,applicationEventPublisher=applicationEventPublisher)

        var exceptionThrown: Boolean = false
        var exception : Exception? = null
        try{
            orderServiceImpl.create(orderDto)
        }catch (ex: Exception){
            exception = ex
            exceptionThrown=true
            print("exception occurred")
        }

        assertTrue(exceptionThrown)
        assertInstanceOf(OrderException::class.java, exception)
    }

    @Test
    fun `test order was created successfully`(){
        val user = User()
        user.id = 1
        user.deposit= 100

        val orderDto = OrderDto()
        orderDto.amountOfProduct=2;
        orderDto.productId=1
        orderDto.setPrincipalUser(user)

        val product = Product()
        product.amountAvailable= 5
        product.id=1
        product.cost=10

        val savedOrder = Order()
        savedOrder.totalCost=200
        savedOrder.product=product


        val productService = mockk<ProductService>{
            every { findById(any()) } returns product
        }
        val coinFrequencyService = mockk<CoinFrequencyService> {
            every { getCoinsLessThanOrEquals(any()) } returns getCoinsList()
        }
        val orderRepository = mockk<OrderRepository>{
            every { save(any()) } returns savedOrder
        }
        val applicationEventPublisher = mockk<ApplicationEventPublisher>(){
            every { publishEvent(any()) } returns Unit
        }
        val orderServiceImpl = OrderServiceImpl(productService=productService,coinFrequencyService=coinFrequencyService,orderRepository=orderRepository,applicationEventPublisher=applicationEventPublisher)

        val orderResponseDto =orderServiceImpl.create(orderDto)

        val slot = slot<Order>()
        verify(exactly = 1) {
            orderRepository.save(capture(slot))
        }
        assertEquals(OrderStatus.SUCCESS.value, slot.captured.status)
        assertEquals(user, slot.captured.buyer)
    }
    private fun getCoinsList() : List<CoinFrequency>{
        val list = ArrayList<CoinFrequency>()

        var coinFrequency = CoinFrequency()
        coinFrequency.value = 100
        coinFrequency.frequency=10
        list.add(coinFrequency)

        coinFrequency = CoinFrequency()
        coinFrequency.value = 50
        coinFrequency.frequency=10
        list.add(coinFrequency)

        coinFrequency = CoinFrequency()
        coinFrequency.value = 20
        coinFrequency.frequency=10
        list.add(coinFrequency)

        coinFrequency = CoinFrequency()
        coinFrequency.value = 10
        coinFrequency.frequency=10
        list.add(coinFrequency)

        coinFrequency = CoinFrequency()
        coinFrequency.value = 5
        coinFrequency.frequency=10
        list.add(coinFrequency)

        return list
    }

    private fun calculateChange(change : Int, coinsAvailable : List<CoinFrequency>) : List<Int>{
        val listOfChange = ArrayList<Int>()
        var userChange = change
        var iterator = 0
        var sum = 0
        while(userChange != 0 && iterator < coinsAvailable.size){
            val highestDenomination  = coinsAvailable[iterator]
            if(highestDenomination.value > userChange || highestDenomination.frequency == 0){
                iterator ++
                continue
            }
            sum += highestDenomination.value
            listOfChange.add(highestDenomination.value)
            userChange -= highestDenomination.value
            highestDenomination.frequency--
        }
        println("sum ==> $sum")
        println("userChange ==> $userChange")
        return listOfChange
    }

}