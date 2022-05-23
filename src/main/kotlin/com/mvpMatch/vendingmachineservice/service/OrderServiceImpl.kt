package com.mvpMatch.vendingmachineservice.service

import com.mvpMatch.vendingmachineservice.exceptions.OrderException
import com.mvpMatch.vendingmachineservice.model.CoinFrequency
import com.mvpMatch.vendingmachineservice.model.dtos.OrderDto
import com.mvpMatch.vendingmachineservice.model.dtos.OrderResponseDto
import org.springframework.stereotype.Service

@Service
class OrderServiceImpl(private val productService: ProductService,
                       private val coinFrequencyService: CoinFrequencyService) : OrderService {

    override fun create(orderDto: OrderDto): OrderResponseDto {
        // check if the product amount is available
        val product = productService.findById(orderDto.productId.toLong())

        if(product.amountAvailable < orderDto.amountOfProduct)
            throw OrderException("product quantity not available ","")

        // check if  he has enough money to buy the product
        val totalCost = product.cost * orderDto.amountOfProduct
        if(orderDto.getPrincipalUser().deposit < totalCost)
            throw OrderException("Insufficient Fund","")

        // check if there is enough change for the customer
        val userChange = orderDto.getPrincipalUser().deposit - totalCost
        val coinFrequencyList = coinFrequencyService.getCoinsLessThanOrEquals(userChange)
        if(changeAvailable(userChange,coinFrequencyList))
            throw OrderException("Insufficient Fund","")

        TODO("Not yet implemented")
    }

    private fun changeAvailable(change : Int, coinsAvailable : List<CoinFrequency>) : Boolean{
        val availableChange = coinsAvailable.sumOf { coin ->coin.value * coin.frequency }
        return availableChange >= change
    }


}