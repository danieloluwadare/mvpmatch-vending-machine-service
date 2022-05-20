package com.mvpMatch.vendingmachineservice.service

import com.mvpMatch.vendingmachineservice.exceptions.AuthenticationException
import com.mvpMatch.vendingmachineservice.model.User
import com.mvpMatch.vendingmachineservice.model.dtos.JwtTokenDto
import com.mvpMatch.vendingmachineservice.model.dtos.UserLoginDto
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthenticationServiceImpl(private val tokenGenerator: TokenGenerator,
private val userService: UserService,
private val passwordEncoder: PasswordEncoder
) : AuthenticationService {

    override fun authenticate(userLoginDto: UserLoginDto): JwtTokenDto {
        val user = userService.findByUsername(userLoginDto.username) ?: throw AuthenticationException("username not found", "")
        if (!comparePassword(userLoginDto.password, user.password)) {
            throw AuthenticationException("password mismatch", "")
        }
        return tokenGenerator.generate(user.id.toString())
    }

    fun comparePassword(password: String, actualPassword: String): Boolean {
        return passwordEncoder.matches(password, actualPassword)
    }
}