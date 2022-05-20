package com.mvpMatch.vendingmachineservice.service

import com.mvpMatch.vendingmachineservice.exceptions.UserRegistartionException
import com.mvpMatch.vendingmachineservice.model.User
import com.mvpMatch.vendingmachineservice.model.dtos.UserRegistrationDto
import com.mvpMatch.vendingmachineservice.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(private val userRepository: UserRepository) : UserService {
    override fun create(userRegistrationDto: UserRegistrationDto): User {
        val existingUser = findByUsername(userRegistrationDto.username)
        if(existingUser != null) throw UserRegistartionException("username already exist","")
        val user = User();
        user.role = userRegistrationDto.role;
        user.username = userRegistrationDto.username
        user.password = userRegistrationDto.password
        return userRepository.save(user);
    }

    override fun findByUsername(userName: String): User? {
        return userRepository.findByUsername(userName)
    }
}