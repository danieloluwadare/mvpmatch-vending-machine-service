package com.mvpMatch.vendingmachineservice.service

import com.mvpMatch.vendingmachineservice.model.User
import com.mvpMatch.vendingmachineservice.model.dtos.UserRegistrationDto
import com.mvpMatch.vendingmachineservice.repository.UserRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class UserServiceImplTest{
    //MethodArgumentNotValidException

    @Test
    fun `create invokes userRepository save method`() {

        val userRegistrationDto = UserRegistrationDto()

        userRegistrationDto.password="password"
        userRegistrationDto.role="seller"
        userRegistrationDto.username="mvpmatch"

        val userRepository = mockk<UserRepository> {
            every { save(any()) }returns User()
        }

        val userServiceImpl = UserServiceImpl(userRepository = userRepository)
        userServiceImpl.create(userRegistrationDto)

        verify(exactly = 1) {
            userRepository.save(any())
        }
    }

}