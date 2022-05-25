package com.mvpMatch.vendingmachineservice.service

import com.mvpMatch.vendingmachineservice.model.dtos.OrderDto
import com.mvpMatch.vendingmachineservice.model.dtos.OrderResponseDto

interface OrderService {
    fun create(orderDto: OrderDto): OrderResponseDto
}