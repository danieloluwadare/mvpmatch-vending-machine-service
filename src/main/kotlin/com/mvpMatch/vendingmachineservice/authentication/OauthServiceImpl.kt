package com.mvpMatch.vendingmachineservice.authentication

import com.mvpMatch.vendingmachineservice.exceptions.AuthenticationException
import com.mvpMatch.vendingmachineservice.exceptions.UserSessionActiveException
import com.mvpMatch.vendingmachineservice.model.dtos.JwtTokenDto
import com.mvpMatch.vendingmachineservice.model.dtos.UserLoginDto
import com.mvpMatch.vendingmachineservice.service.UserService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class OauthServiceImpl(
    private val tokenService: TokenService,
    private val userService: UserService,
    private val passwordEncoder: PasswordEncoder
) : OauthService {

    override fun authenticate(userLoginDto: UserLoginDto): JwtTokenDto {
        val user =
            userService.findByUsername(userLoginDto.username) ?: throw AuthenticationException("username not found", "")
        if (!comparePassword(userLoginDto.password, user.password)) {
            throw AuthenticationException("password mismatch", "")
        }
        if (user.sessionActive == 1)
            throw UserSessionActiveException("Session is already active for user ${user.username}", "")
        userService.activateSession(user.id)
        return tokenService.generate(user.username)
    }

    fun comparePassword(password: String, actualPassword: String): Boolean {
        return passwordEncoder.matches(password, actualPassword)
    }

    override fun invalidate(id: Long): Map<String, Any> {
        userService.deActivateSession(id)
        return mapOf<String, Any>("data" to "Session Invalidated successful")
    }
}