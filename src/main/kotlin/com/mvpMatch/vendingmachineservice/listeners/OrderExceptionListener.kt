package com.mvpMatch.vendingmachineservice.listeners

import com.mvpMatch.vendingmachineservice.events.OrderInitiatedEvent
import com.mvpMatch.vendingmachineservice.service.CoinFrequencyService
import com.mvpMatch.vendingmachineservice.service.UserService
import org.slf4j.LoggerFactory
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class OrderExceptionListener(
    private val coinFrequencyService: CoinFrequencyService,
    private val userService: UserService
) {
    private val log = LoggerFactory.getLogger(javaClass)

    @EventListener(OrderInitiatedEvent::class)
    fun reportDepositAction(event: OrderInitiatedEvent) {
        log.info(">> begin reportDepositAction")
//        coinFrequencyService.increaseFrequency(event.depositDto.amount)
        coinFrequencyService.update(event.coinFrequencyList)
        userService.resetDeposit(event.orderDto.getPrincipalUser().username)
        log.info(">> done reportDepositAction")
    }
}