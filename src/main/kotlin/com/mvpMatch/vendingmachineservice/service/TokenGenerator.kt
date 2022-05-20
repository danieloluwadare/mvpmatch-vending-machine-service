package com.mvpMatch.vendingmachineservice.service

import com.mvpMatch.vendingmachineservice.model.User
import com.mvpMatch.vendingmachineservice.model.dtos.JwtTokenDto

interface TokenGenerator {
    fun generate(userUniqueIdentifier : String) : JwtTokenDto
    fun generate(user : User) : JwtTokenDto
}