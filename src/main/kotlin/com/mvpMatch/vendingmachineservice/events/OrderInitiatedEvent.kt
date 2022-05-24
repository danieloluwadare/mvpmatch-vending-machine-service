package com.mvpMatch.vendingmachineservice.events

import com.mvpMatch.vendingmachineservice.model.CoinFrequency
import com.mvpMatch.vendingmachineservice.model.dtos.OrderDto
import org.springframework.context.ApplicationEvent

class OrderInitiatedEvent(source: Any, val orderDto: OrderDto, val coinFrequencyList: List<CoinFrequency>) :
    ApplicationEvent(source) {
}