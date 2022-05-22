package com.mvpMatch.vendingmachineservice.service

import com.mvpMatch.vendingmachineservice.exceptions.UserDepositException
import com.mvpMatch.vendingmachineservice.exceptions.UserRegistrationException
import com.mvpMatch.vendingmachineservice.model.User
import com.mvpMatch.vendingmachineservice.model.dtos.DepositDto
import com.mvpMatch.vendingmachineservice.model.dtos.UserRegistrationDto
import com.mvpMatch.vendingmachineservice.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(private val userRepository: UserRepository) : UserService {
    private val validDepositAmount = hashSetOf(5, 10, 20, 50, 100);
    override fun create(userRegistrationDto: UserRegistrationDto): User {
        val existingUser = findByUsername(userRegistrationDto.username)
        if(existingUser != null) throw UserRegistrationException("user with username already exist","")
        val user = User();
        user.role = userRegistrationDto.role;
        user.username = userRegistrationDto.username
        user.password = userRegistrationDto.password
        return userRepository.save(user);
    }
    override fun findByUsername(userName: String): User? {
        return userRepository.findByUsername(userName)
    }
    override fun deposit(depositDto: DepositDto): User {
        if(!validDepositAmount.contains(depositDto.amount) )
            throw UserDepositException("deposit amount accepted are 5, 10, 20, 50, 100 cents","")
        val user = depositDto.getPrincipalUser();
        user.deposit = user.deposit + depositDto.amount;
        return userRepository.save(user)
    }
}