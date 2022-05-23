package com.mvpMatch.vendingmachineservice.service

import com.mvpMatch.vendingmachineservice.repository.CoinFrequencyRepository
import org.springframework.stereotype.Service

@Service
class CoinFrequencyServiceImpl(private val coinFrequencyRepository: CoinFrequencyRepository) : CoinFrequencyService {
    override fun increaseFrequency(value: Int) {
        val coinFrequency = coinFrequencyRepository.findByValueAndDeletedAtIsNull(value)
        coinFrequency.frequency++
        coinFrequencyRepository.save(coinFrequency)
    }
}