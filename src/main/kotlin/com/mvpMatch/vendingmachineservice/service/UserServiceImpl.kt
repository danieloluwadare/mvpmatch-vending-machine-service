package com.mvpMatch.vendingmachineservice.service

import com.mvpMatch.vendingmachineservice.model.User
import com.mvpMatch.vendingmachineservice.model.dtos.UserRegistrationDto
import org.springframework.stereotype.Service

@Service
class UserServiceImpl : UserService {
    override fun create(userRegistrationDto: UserRegistrationDto): User {
        TODO("Not yet implemented")
    }

}