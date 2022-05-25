package com.mvpMatch.vendingmachineservice.utils

import com.mvpMatch.vendingmachineservice.enums.OrderStatus
import com.mvpMatch.vendingmachineservice.model.CoinFrequency
import com.mvpMatch.vendingmachineservice.model.Order
import com.mvpMatch.vendingmachineservice.model.Product
import com.mvpMatch.vendingmachineservice.model.dtos.OrderDto
import com.mvpMatch.vendingmachineservice.model.dtos.OrderResponseDto
import org.apache.logging.log4j.util.Strings
import java.util.*
import java.util.stream.Collectors

class OrderUtils {
    companion object {
        fun calculate(change: Int, coinsAvailable: List<CoinFrequency>): List<Int> {
            val listOfChange = ArrayList<Int>()
            var userChange = change
            var iterator = 0
            var sum = 0
            while (userChange != 0 && iterator < coinsAvailable.size) {
                val highestDenomination = coinsAvailable[iterator]
                if (highestDenomination.value > userChange || highestDenomination.frequency == 0) {
                    iterator++
                    continue
                }
                sum += highestDenomination.value
                listOfChange.add(highestDenomination.value)
                userChange -= highestDenomination.value
                highestDenomination.frequency--
            }
            return listOfChange
        }

        fun formatResponse(order: Order): OrderResponseDto {
            val response = OrderResponseDto()
            response.totalCost = order.totalCost
            response.product = order.product.productName
            if (Strings.isNotBlank(order.userChangeDenomination))
                response.change = order.userChangeDenomination
                    .split(',').stream().map { coin -> Integer.parseInt(coin) }.collect(Collectors.toList())

            return response
        }

        fun createOrder(
            product: Product,
            totalCost: Int,
            reference: String,
            orderDto: OrderDto,
            coins: List<Int>,
            coinsInStringFormat: StringJoiner
        ): Order {
            val order = Order()
            order.amountOfProduct = order.amountOfProduct
            order.product = product
            order.totalCost = totalCost
            order.reference = reference
            order.buyer = orderDto.getPrincipalUser()
            order.status = OrderStatus.SUCCESS.value
            order.message = "$reference ${OrderStatus.SUCCESS.value}"
            order.userChange = coins.sum()
            order.userChangeDenomination = coinsInStringFormat.toString()
            return order
        }


    }
}