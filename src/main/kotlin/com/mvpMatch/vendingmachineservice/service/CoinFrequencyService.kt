package com.mvpMatch.vendingmachineservice.service

import com.mvpMatch.vendingmachineservice.model.CoinFrequency
import com.mvpMatch.vendingmachineservice.model.Product
import com.mvpMatch.vendingmachineservice.model.dtos.ProductDto

interface CoinFrequencyService {
    fun increaseFrequency(value: Int)
    fun update(coinFrequencies : List<CoinFrequency>)
    fun getCoinsLessThanOrEquals(value: Int) :List<CoinFrequency>
}