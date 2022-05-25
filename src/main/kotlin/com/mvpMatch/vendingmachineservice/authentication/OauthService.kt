package com.mvpMatch.vendingmachineservice.authentication

import com.mvpMatch.vendingmachineservice.model.dtos.JwtTokenDto
import com.mvpMatch.vendingmachineservice.model.dtos.UserLoginDto

interface OauthService {
    fun authenticate(userLoginDto: UserLoginDto): JwtTokenDto
    fun invalidate(id: Long): Map<String, Any>
}