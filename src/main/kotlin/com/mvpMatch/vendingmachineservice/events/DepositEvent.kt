package com.mvpMatch.vendingmachineservice.events

import com.mvpMatch.vendingmachineservice.model.dtos.DepositDto
import org.springframework.context.ApplicationEvent

class DepositEvent(source: Any, val depositDto: DepositDto) : ApplicationEvent(source)