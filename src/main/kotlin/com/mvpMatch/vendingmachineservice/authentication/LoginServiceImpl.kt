package com.mvpMatch.vendingmachineservice.authentication

import com.mvpMatch.vendingmachineservice.exceptions.AuthenticationException
import com.mvpMatch.vendingmachineservice.model.User
import com.mvpMatch.vendingmachineservice.model.dtos.JwtTokenDto
import com.mvpMatch.vendingmachineservice.model.dtos.UserLoginDto
import com.mvpMatch.vendingmachineservice.service.UserService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class LoginServiceImpl(private val tokenService: TokenService,
                       private val userService: UserService,
                       private val passwordEncoder: PasswordEncoder
) : LoginService {

    override fun authenticate(userLoginDto: UserLoginDto): JwtTokenDto {
        val user = userService.findByUsername(userLoginDto.username) ?: throw AuthenticationException("username not found", "")
        if (!comparePassword(userLoginDto.password, user.password)) {
            throw AuthenticationException("password mismatch", "")
        }
        return tokenService.generate(user.username)
    }

    fun comparePassword(password: String, actualPassword: String): Boolean {
        return passwordEncoder.matches(password, actualPassword)
    }

//     fun getUser(accessToken: String): User {
//        val userIdentifier = tokenService.verify(accessToken)
//        return userService.findByUsername(userIdentifier)!!;
//    }
}