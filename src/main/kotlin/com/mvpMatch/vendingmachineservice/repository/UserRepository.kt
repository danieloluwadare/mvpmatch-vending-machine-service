package com.mvpMatch.vendingmachineservice.repository

import com.mvpMatch.vendingmachineservice.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<User, Long> {
    fun findByUsername(username:String): User?
}