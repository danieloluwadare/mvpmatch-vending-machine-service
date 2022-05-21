package com.mvpMatch.vendingmachineservice.authentication

import com.mvpMatch.vendingmachineservice.model.User
import com.mvpMatch.vendingmachineservice.model.dtos.JwtTokenDto
import com.mvpMatch.vendingmachineservice.model.dtos.UserLoginDto

interface LoginService {
    fun authenticate(userLoginDto:UserLoginDto): JwtTokenDto
}