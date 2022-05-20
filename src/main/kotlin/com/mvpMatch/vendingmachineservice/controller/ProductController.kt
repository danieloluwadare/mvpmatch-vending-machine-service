package com.mvpMatch.vendingmachineservice.controller

import com.mvpMatch.vendingmachineservice.annotations.EnableMvpSecurity
import com.mvpMatch.vendingmachineservice.model.User
import com.mvpMatch.vendingmachineservice.model.dtos.UserRegistrationDto
import com.mvpMatch.vendingmachineservice.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("api/v1/user","v1/product")
class ProductController(private val userService: UserService) {

    @EnableMvpSecurity()
    @PostMapping("")
    fun register(@Valid @RequestBody user: UserRegistrationDto): ResponseEntity<User> {
        return ResponseEntity.ok(this.userService.create(user))
    }
}