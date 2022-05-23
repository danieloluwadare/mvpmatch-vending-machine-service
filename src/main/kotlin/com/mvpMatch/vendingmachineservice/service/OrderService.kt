package com.mvpMatch.vendingmachineservice.service

import com.mvpMatch.vendingmachineservice.model.User
import com.mvpMatch.vendingmachineservice.model.dtos.DepositDto
import com.mvpMatch.vendingmachineservice.model.dtos.UserRegistrationDto

interface OrderService {
    fun create(userRegistrationDto : UserRegistrationDto):User
    fun findByUsername(userName:String) : User?
    fun deposit(depositDto: DepositDto) : User
}