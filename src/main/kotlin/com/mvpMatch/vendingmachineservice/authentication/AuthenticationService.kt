package com.mvpMatch.vendingmachineservice.authentication

import com.mvpMatch.vendingmachineservice.model.User
import com.mvpMatch.vendingmachineservice.model.dtos.JwtTokenDto
import com.mvpMatch.vendingmachineservice.model.dtos.UserLoginDto

interface AuthenticationService {
    fun authenticate(userLoginDto:UserLoginDto): JwtTokenDto
    fun getUser(accessToken : String): User
}