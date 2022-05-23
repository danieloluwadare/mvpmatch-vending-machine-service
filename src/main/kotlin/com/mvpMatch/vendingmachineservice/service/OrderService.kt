package com.mvpMatch.vendingmachineservice.service

import com.mvpMatch.vendingmachineservice.model.User
import com.mvpMatch.vendingmachineservice.model.dtos.DepositDto
import com.mvpMatch.vendingmachineservice.model.dtos.OrderDto
import com.mvpMatch.vendingmachineservice.model.dtos.OrderResponseDto
import com.mvpMatch.vendingmachineservice.model.dtos.UserRegistrationDto

interface OrderService {
    fun create(orderDto: OrderDto): OrderResponseDto
}