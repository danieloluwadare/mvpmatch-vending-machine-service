package com.mvpMatch.vendingmachineservice.service

import com.mvpMatch.vendingmachineservice.model.User
import com.mvpMatch.vendingmachineservice.model.dtos.JwtTokenDto

interface TokenService {
    fun generate(userUniqueIdentifier : String) : JwtTokenDto
    fun generate(user : User) : JwtTokenDto
    fun verify(accessToken : String) : String
}