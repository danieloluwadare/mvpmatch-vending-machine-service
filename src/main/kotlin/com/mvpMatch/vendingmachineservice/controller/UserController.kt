package com.mvpMatch.vendingmachineservice.controller

import com.mvpMatch.vendingmachineservice.model.User
import com.mvpMatch.vendingmachineservice.model.dtos.UserRegistrationDto
import com.mvpMatch.vendingmachineservice.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/user","v1/user")
class UserController(private val userService: UserService) {

    @PostMapping("create")
    fun register(@RequestBody user: UserRegistrationDto): ResponseEntity<User> {
        return ResponseEntity.ok(this.userService.create(user))
    }
}