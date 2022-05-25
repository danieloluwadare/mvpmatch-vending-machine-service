package com.mvpMatch.vendingmachineservice.controller

import com.mvpMatch.vendingmachineservice.authentication.OauthService
import com.mvpMatch.vendingmachineservice.model.dtos.JwtTokenDto
import com.mvpMatch.vendingmachineservice.model.dtos.UserLoginDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("api/v1/oauth", "v1/oauth")
class AuthenticationController(private val oauthService: OauthService) {

    @PostMapping("token")
    fun login(@Valid @RequestBody user: UserLoginDto): ResponseEntity<JwtTokenDto> {
        return ResponseEntity.ok(this.oauthService.authenticate(user))
    }

    @PostMapping("logout/all/{id}")
    fun logout(@PathVariable id: Long): ResponseEntity<Map<String, Any>> {
        return ResponseEntity.ok(this.oauthService.invalidate(id))
    }
}