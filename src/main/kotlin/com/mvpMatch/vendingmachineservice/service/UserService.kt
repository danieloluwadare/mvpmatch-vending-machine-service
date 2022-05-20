package com.mvpMatch.vendingmachineservice.service

import com.mvpMatch.vendingmachineservice.model.User
import com.mvpMatch.vendingmachineservice.model.dtos.UserRegistrationDto

interface UserService {
    fun create(userRegistrationDto : UserRegistrationDto):User
    fun findByUsername(userName:String) : User?
}