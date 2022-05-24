package com.mvpMatch.vendingmachineservice.service

import com.mvpMatch.vendingmachineservice.enums.OrderStatus
import com.mvpMatch.vendingmachineservice.events.OrderInitiatedEvent
import com.mvpMatch.vendingmachineservice.exceptions.OrderException
import com.mvpMatch.vendingmachineservice.model.CoinFrequency
import com.mvpMatch.vendingmachineservice.model.Order
import com.mvpMatch.vendingmachineservice.model.Product
import com.mvpMatch.vendingmachineservice.model.dtos.OrderDto
import com.mvpMatch.vendingmachineservice.model.dtos.OrderResponseDto
import com.mvpMatch.vendingmachineservice.repository.OrderRepository
import com.mvpMatch.vendingmachineservice.utils.OrderUtils
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import java.util.*

@Service
class OrderServiceImpl(
    private val productService: ProductService,
    private val coinFrequencyService: CoinFrequencyService,
    private val orderRepository: OrderRepository,
    private val applicationEventPublisher: ApplicationEventPublisher
) : OrderService {

    override fun create(orderDto: OrderDto): OrderResponseDto {
        val reference = UUID.randomUUID().toString()
        val product = productService.findById(orderDto.productId.toLong())
        val totalCost = product.cost * orderDto.amountOfProduct
        // check if the product amount is available
        validateProductAvailability(orderDto, product)
        // check if buyer has enough money to buy the product
        validateUserHasSufficientFund(orderDto, totalCost, product)
        // check if there is enough change for the customer
        val userChange = orderDto.getPrincipalUser().deposit - totalCost
        val coinFrequencyList = coinFrequencyService.getCoinsLessThanOrEquals(userChange)
        val coins = getUserChange(orderDto, userChange, coinFrequencyList, product)

        val coinsInStringFormat = StringJoiner(",")
        coins.forEach { coin -> coinsInStringFormat.add(coin.toString()) }

        var order = Order()
        order.amountOfProduct = order.amountOfProduct
        order.product = product
        order.totalCost = totalCost
        order.reference = UUID.randomUUID().toString()
        order.buyer = orderDto.getPrincipalUser()
        order.status = OrderStatus.SUCCESS.value
        order.message = "$reference ${OrderStatus.SUCCESS.value}"
        order.userChange = coins.sum()
        order.userChangeDenomination = coinsInStringFormat.toString()

        order = orderRepository.save(order)
        applicationEventPublisher.publishEvent(OrderInitiatedEvent(this, orderDto, coinFrequencyList))
        return OrderUtils.formatResponse(order)
    }

    private fun validateUserHasSufficientFund(
        orderDto: OrderDto,
        totalCost: Int,
        product: Product
    ) {
        if (orderDto.getPrincipalUser().deposit < totalCost)
            initiateException("Insufficient Fund", orderDto, product)
    }

    private fun validateProductAvailability(
        orderDto: OrderDto,
        product: Product
    ) {
        if (product.amountAvailable < orderDto.amountOfProduct)
            initiateException("Product Quantity Not Available", orderDto, product)
    }

    private fun getUserChange(
        orderDto: OrderDto,
        userChange: Int,
        coinFrequencyList: List<CoinFrequency>,
        product: Product
    ): List<Int> {

        if (userChange > 0) {
            val coins = OrderUtils.calculate(userChange, coinFrequencyList)
            if (coins.sum() != userChange)
                initiateException("Insufficient change", orderDto, product)
            return coins
        }
        return emptyList()
    }

    private fun initiateException(
        errorMessage: String,
        orderDto: OrderDto,
        product: Product
    ) {

        //generate a order reference
        val reference = UUID.randomUUID().toString()

        val refundAmount = orderDto.getPrincipalUser().deposit
        val coinFrequencyList = coinFrequencyService.getCoinsLessThanOrEquals(refundAmount)
        val coins = OrderUtils.calculate(refundAmount, coinFrequencyList)
        val coinsInStringFormat = StringJoiner(",")
        coins.forEach { coin -> coinsInStringFormat.add(coin.toString()) }

        val order = Order()
        order.amountOfProduct = order.amountOfProduct
        order.product = product
        order.reference = reference
        order.buyer = orderDto.getPrincipalUser()
        order.status = OrderStatus.FAILED.value
        order.message = errorMessage
        order.userChangeDenomination = coinsInStringFormat.toString()
        order.refundedAmount = coins.sum()

        orderRepository.save(order)

        applicationEventPublisher.publishEvent(OrderInitiatedEvent(this, orderDto, coinFrequencyList))

        // return the users deposit as change
        throw OrderException(errorMessage, "", coins)
    }


}