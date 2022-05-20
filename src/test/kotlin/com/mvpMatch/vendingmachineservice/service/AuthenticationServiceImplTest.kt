package com.mvpMatch.vendingmachineservice.service

import com.mvpMatch.vendingmachineservice.exceptions.AuthenticationException
import com.mvpMatch.vendingmachineservice.model.User
import com.mvpMatch.vendingmachineservice.model.dtos.JwtTokenDto
import com.mvpMatch.vendingmachineservice.model.dtos.UserLoginDto
import com.mvpMatch.vendingmachineservice.model.dtos.UserRegistrationDto
import com.mvpMatch.vendingmachineservice.repository.UserRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.security.crypto.password.PasswordEncoder

class AuthenticationServiceImplTest{

    @Test
    fun `throw authentication Exception when user can't be found`() {

        val user = UserLoginDto()
        user.password="password"
        user.username="mvpmatch"

        val tokenGenerator = mockk<TokenGenerator> {
            every { generate("password") } returns JwtTokenDto("","","")
        }
        val userService = mockk<UserService> {
            every { findByUsername(any()) } returns null
        }
        val passwordEncoder = mockk<PasswordEncoder> {
            every { matches(any(), any()) } returns true
        }

        val authenticationService = AuthenticationServiceImpl(tokenGenerator = tokenGenerator , userService = userService, passwordEncoder=passwordEncoder)

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

        val tokenGenerator = mockk<TokenGenerator> {
            every { generate("password") } returns JwtTokenDto("","","")
        }
        val userService = mockk<UserService> {
            every { findByUsername(any()) } returns User()
        }
        val passwordEncoder = mockk<PasswordEncoder> {
            every { matches(any(), any()) } returns false
        }

        val authenticationService = AuthenticationServiceImpl(tokenGenerator = tokenGenerator , userService = userService, passwordEncoder=passwordEncoder)

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