package com.mvpMatch.vendingmachineservice.listeners

import com.mvpMatch.vendingmachineservice.events.DepositEvent
import com.mvpMatch.vendingmachineservice.service.CoinFrequencyService
import org.slf4j.LoggerFactory
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class DepositListener(private val coinFrequencyService: CoinFrequencyService) {
    private val log = LoggerFactory.getLogger(javaClass)

    @EventListener(DepositEvent::class)
    fun reportDepositAction(event: DepositEvent) {
        log.info(">> begin reportDepositAction")
        coinFrequencyService.increaseFrequency(event.depositDto.amount)
        log.info(">> done reportDepositAction")
    }
}