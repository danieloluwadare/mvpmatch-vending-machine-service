package com.mvpMatch.vendingmachineservice.service

import com.mvpMatch.vendingmachineservice.model.CoinFrequency

interface CoinFrequencyService {
    fun increaseFrequency(value: Int)
    fun update(coinFrequencies: List<CoinFrequency>)
    fun getCoinsLessThanOrEquals(value: Int): List<CoinFrequency>
}