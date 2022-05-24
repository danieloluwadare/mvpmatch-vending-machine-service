package com.mvpMatch.vendingmachineservice.repository

import com.mvpMatch.vendingmachineservice.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository: JpaRepository<User, Long> {
    fun findByUsername(username:String): User?
    @Modifying
    @Query("update User u set u.deposit = :deposit where u.username = :username")
    fun updateDepositByUsername(@Param("deposit")deposit: Int, @Param("username") username: String)

    @Modifying
    @Query("update User u set u.deposit = :deposit where u.id = :userId")
    fun updateDepositByUserId(@Param("deposit")deposit: Int, @Param("userId") userId: Long)
}