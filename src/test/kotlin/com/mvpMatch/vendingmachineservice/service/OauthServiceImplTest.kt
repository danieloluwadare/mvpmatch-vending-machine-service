package com.mvpMatch.vendingmachineservice.service

import com.mvpMatch.vendingmachineservice.authentication.OauthServiceImpl
import com.mvpMatch.vendingmachineservice.authentication.TokenService
import com.mvpMatch.vendingmachineservice.exceptions.AuthenticationException
import com.mvpMatch.vendingmachineservice.model.User
import com.mvpMatch.vendingmachineservice.model.dtos.JwtTokenDto
import com.mvpMatch.vendingmachineservice.model.dtos.UserLoginDto
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.security.crypto.password.PasswordEncoder

class OauthServiceImplTest{

    @Test
    fun `throw authentication Exception when user can't be found`() {

        val user = UserLoginDto()
        user.password="password"
        user.username="mvpmatch"

        val tokenService = mockk<TokenService> {
            every { generate("password") } returns JwtTokenDto("","","")
        }
        val userService = mockk<UserService> {
            every { findByUsername(any()) } returns null
        }
        val passwordEncoder = mockk<PasswordEncoder> {
            every { matches(any(), any()) } returns true
        }

        val authenticationService = OauthServiceImpl(tokenService = tokenService , userService = userService, passwordEncoder=passwordEncoder)

        var exceptionThrown: Boolean = false
        var exception : Exception? = null
        try{
            authenticationService.authenticate(user)
        }catch (ex: Exception){
            exception = ex
            exceptionThrown=true
            print("exception occurred")
        }

        assertTrue(exceptionThrown)
        assertInstanceOf(AuthenticationException::class.java, exception)

    }



    @Test
    fun `throw authentication Exception when password do not match`() {

        val user = UserLoginDto()
        user.password="password"
        user.username="mvpmatch"

        val tokenService = mockk<TokenService> {
            every { generate("password") } returns JwtTokenDto("","","")
        }
        val userService = mockk<UserService> {
            every { findByUsername(any()) } returns User()
        }
        val passwordEncoder = mockk<PasswordEncoder> {
            every { matches(any(), any()) } returns false
        }

        val authenticationService = OauthServiceImpl(tokenService = tokenService , userService = userService, passwordEncoder=passwordEncoder)

        var exceptionThrown: Boolean = false
        var exception : Exception? = null
        try{
            authenticationService.authenticate(user)
        }catch (ex: Exception){
            exception = ex
            exceptionThrown=true
            print("exception occurred")
        }

        assertTrue(exceptionThrown)
        assertInstanceOf(AuthenticationException::class.java, exception)
    }
}