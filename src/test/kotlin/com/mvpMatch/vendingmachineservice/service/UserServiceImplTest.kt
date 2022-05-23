package com.mvpMatch.vendingmachineservice.service

import com.mvpMatch.vendingmachineservice.exceptions.UnAuthorizedUserException
import com.mvpMatch.vendingmachineservice.exceptions.UserDepositException
import com.mvpMatch.vendingmachineservice.model.User
import com.mvpMatch.vendingmachineservice.model.dtos.DepositDto
import com.mvpMatch.vendingmachineservice.model.dtos.UserRegistrationDto
import com.mvpMatch.vendingmachineservice.repository.UserRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.context.ApplicationEventPublisher

class UserServiceImplTest{

    @Test
    fun `create invokes userRepository save method`() {

        val userRegistrationDto = UserRegistrationDto()

        userRegistrationDto.password="password"
        userRegistrationDto.role="seller"
        userRegistrationDto.username="mvpmatch"

        val userRepository = mockk<UserRepository> {
            every { save(any()) }returns User()
            every { findByUsername(any()) } returns null
        }

        val applicationEventPublisher = mockk<ApplicationEventPublisher>()

        val userServiceImpl = UserServiceImpl(userRepository = userRepository, applicationEventPublisher=applicationEventPublisher)
        userServiceImpl.create(userRegistrationDto)

        verify(exactly = 1) {
            userRepository.save(any())
        }
    }
    @Test
    fun `throw UserDepositException when deposit amount is invalid`(){
        val depositDto = DepositDto()
        depositDto.amount=40;

        val userRepository = mockk<UserRepository> {
            every { save(any()) } returns User()
        }
        val applicationEventPublisher = mockk<ApplicationEventPublisher>(){
            every { publishEvent(any()) } returns Unit
        }
        val userServiceImpl = UserServiceImpl(userRepository = userRepository, applicationEventPublisher=applicationEventPublisher)

        var exceptionThrown: Boolean = false
        var exception : Exception? = null
        try{
            userServiceImpl.deposit(depositDto)
        }catch (ex: Exception){
            exception = ex
            exceptionThrown=true
            print("exception occurred")
        }

        assertTrue(exceptionThrown)
        assertInstanceOf(UserDepositException::class.java, exception)
    }

    @Test
    fun `user deposit increases by deposit amount when deposit amount is valid`(){
        val user = User()
        user.id = 1
        user.deposit= 0

        val depositDto = DepositDto()
        depositDto.amount=10;
        depositDto.setPrincipalUser(user)

        val userRepository = mockk<UserRepository> {
            every { save(any()) } returns User()
        }
        val applicationEventPublisher = mockk<ApplicationEventPublisher>(){
            every { publishEvent(any()) } returns Unit
        }
        val userServiceImpl = UserServiceImpl(userRepository = userRepository, applicationEventPublisher=applicationEventPublisher)
        userServiceImpl.deposit(depositDto)

        val slot = slot<User>()
        verify(exactly = 1) {
            userRepository.save(capture(slot))
        }
        assertEquals(10, slot.captured.deposit)
    }

}