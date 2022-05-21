package com.mvpMatch.vendingmachineservice.controller

import com.mvpMatch.vendingmachineservice.model.dtos.JwtTokenDto
import com.mvpMatch.vendingmachineservice.model.dtos.UserLoginDto
import com.mvpMatch.vendingmachineservice.authentication.LoginService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("api/v1/oauth","v1/oauth")
class AuthenticationController(private val loginService: LoginService) {

    @PostMapping("token")
    fun register(@Valid @RequestBody user: UserLoginDto): ResponseEntity<JwtTokenDto> {
        return ResponseEntity.ok(this.loginService.authenticate(user))
    }
}