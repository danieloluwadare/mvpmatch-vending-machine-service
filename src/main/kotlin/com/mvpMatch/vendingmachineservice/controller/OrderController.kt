package com.mvpMatch.vendingmachineservice.controller

import com.mvpMatch.vendingmachineservice.annotations.EnableMvpSecurity
import com.mvpMatch.vendingmachineservice.enums.RoleType
import com.mvpMatch.vendingmachineservice.model.dtos.OrderDto
import com.mvpMatch.vendingmachineservice.model.dtos.OrderResponseDto
import com.mvpMatch.vendingmachineservice.service.OrderService
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("api/v1/order", "v1/order")
class OrderController(private val orderService: OrderService) {
    private val log = LoggerFactory.getLogger(javaClass)

    @EnableMvpSecurity(hasAuthority = RoleType.BUYER)
    @PostMapping("buy")
    fun create(
        @RequestHeader("Authorization") token: String,
        @Valid @RequestBody orderDto: OrderDto
    ): ResponseEntity<OrderResponseDto> {
        return ResponseEntity.ok(orderService.create(orderDto))
    }

}