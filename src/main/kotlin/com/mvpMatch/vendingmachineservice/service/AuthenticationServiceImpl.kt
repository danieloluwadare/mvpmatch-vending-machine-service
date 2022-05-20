package com.mvpMatch.vendingmachineservice.service

import com.mvpMatch.vendingmachineservice.exceptions.AuthenticationException
import com.mvpMatch.vendingmachineservice.model.User
import com.mvpMatch.vendingmachineservice.model.dtos.JwtTokenDto
import com.mvpMatch.vendingmachineservice.model.dtos.UserLoginDto
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class AuthenticationServiceImpl(private val tokenGenerator: TokenGenerator,
private val userService: UserService) : AuthenticationService {

    override fun authenticate(userLoginDto: UserLoginDto): JwtTokenDto {
        val user = userService.findByUsername(userLoginDto.username) ?: throw AuthenticationException("username not found", "")
        if (!user.comparePassword(userLoginDto.password)) {
            throw AuthenticationException("password mismatch", "")
        }
        return tokenGenerator.generate(user.id.toString())
    }
}