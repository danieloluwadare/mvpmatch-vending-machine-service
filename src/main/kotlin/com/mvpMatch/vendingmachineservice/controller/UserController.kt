package com.mvpMatch.vendingmachineservice.controller

import com.mvpMatch.vendingmachineservice.annotations.EnableMvpSecurity
import com.mvpMatch.vendingmachineservice.enums.RoleType
import com.mvpMatch.vendingmachineservice.model.User
import com.mvpMatch.vendingmachineservice.model.dtos.DepositDto
import com.mvpMatch.vendingmachineservice.model.dtos.ResetDto
import com.mvpMatch.vendingmachineservice.model.dtos.UserRegistrationDto
import com.mvpMatch.vendingmachineservice.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("api/v1/user","v1/user")
class UserController(private val userService: UserService) {

    @PostMapping("")
    fun register(@Valid @RequestBody user: UserRegistrationDto): ResponseEntity<User> {
        return ResponseEntity.ok(this.userService.create(user))
    }
    @EnableMvpSecurity(
        hasAuthority = RoleType.BUYER
    )
    @PostMapping("deposit")
    fun update(
        @RequestHeader("Authorization") token: String,
        @Valid @RequestBody depositDto: DepositDto,
    ): ResponseEntity<User> {
        return ResponseEntity.ok(userService.deposit(depositDto))
    }

    @EnableMvpSecurity(
        hasAuthority = RoleType.BUYER
    )
    @PutMapping("reset")
    fun resetDeposit(
        @RequestHeader("Authorization") token: String,
        @RequestBody resetDto: ResetDto
    ): ResponseEntity<User> {
        return ResponseEntity.ok(userService.resetDeposit(resetDto.getPrincipalUser().id))
    }
}