package com.mvpMatch.vendingmachineservice.config

import com.mvpMatch.vendingmachineservice.model.CoinFrequency
import com.mvpMatch.vendingmachineservice.repository.CoinFrequencyRepository
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class CoinInitializerConfig(private val coinFrequencyRepository: CoinFrequencyRepository) : CommandLineRunner {
    private val log = LoggerFactory.getLogger(javaClass)
    private val validDepositAmount = hashSetOf(5, 10, 20, 50, 100);

    override fun run(vararg args: String?) {
        initializeCoins()
    }

    fun initializeCoins(){
        log.info(">> begin Initializing coins")
        val coinFrequencies = coinFrequencyRepository.findAll()
        if(coinFrequencies.isNotEmpty()){
            coinFrequencies.forEach {coinFrequency ->
                coinFrequency.frequency=10
            }
            coinFrequencyRepository.saveAll(coinFrequencies)
        }else{
            val coinFrequencyList = ArrayList<CoinFrequency>(5)
            validDepositAmount.forEach { coin ->
                val coinFrequency = CoinFrequency()
                coinFrequency.value=coin;
                coinFrequency.frequency=10
                coinFrequencyList.add(coinFrequency)
            }
            coinFrequencyRepository.saveAll(coinFrequencyList)
        }

        log.info(">> done Initializing coins")

    }
}