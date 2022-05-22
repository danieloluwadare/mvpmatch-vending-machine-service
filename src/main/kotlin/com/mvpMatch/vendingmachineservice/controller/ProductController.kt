package com.mvpMatch.vendingmachineservice.controller

import com.mvpMatch.vendingmachineservice.annotations.EnableMvpSecurity
import com.mvpMatch.vendingmachineservice.enums.RoleType
import com.mvpMatch.vendingmachineservice.enums.ValidationRuleType
import com.mvpMatch.vendingmachineservice.model.Product
import com.mvpMatch.vendingmachineservice.model.User
import com.mvpMatch.vendingmachineservice.model.dtos.ProductDto
import com.mvpMatch.vendingmachineservice.model.dtos.UserRegistrationDto
import com.mvpMatch.vendingmachineservice.repository.UserRepository
import com.mvpMatch.vendingmachineservice.service.ProductService
import com.mvpMatch.vendingmachineservice.service.UserService
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("api/v1/product", "v1/product")
class ProductController(private val productService: ProductService, private val userRepository: UserRepository) {
    private val log = LoggerFactory.getLogger(javaClass)

    @EnableMvpSecurity(hasAuthority = RoleType.SELLER)
    @PostMapping("")
    fun create(
        @RequestHeader("Authorization") token: String,
        @Valid @RequestBody productDto: ProductDto
    ): ResponseEntity<Product> {
        return ResponseEntity.ok(productService.create(productDto))
    }

    @EnableMvpSecurity
    @GetMapping("/{id}")
    fun get(@RequestHeader("Authorization") token: String, @PathVariable id: Long): ResponseEntity<Product> {
        return ResponseEntity.ok(productService.findById(id))
    }

    @EnableMvpSecurity
    @GetMapping("")
    fun getAll(@RequestHeader("Authorization") token: String): ResponseEntity<List<Product>> {
        return ResponseEntity.ok(productService.findAll())
    }

    @EnableMvpSecurity(
        hasAuthority = RoleType.SELLER,
        hasAnySpecificValidationRules = [ValidationRuleType.SELLER_AUTHORIZATION_RULE]
    )
    @PutMapping("/{id}")
    fun update(
        @RequestHeader("Authorization") token: String,
        @Valid @RequestBody productDto: ProductDto,
        @PathVariable id: Long
    ): ResponseEntity<Product> {
        return ResponseEntity.ok(productService.update(productDto, id))
    }

    @EnableMvpSecurity(
        hasAuthority = RoleType.SELLER,
        hasAnySpecificValidationRules = [ValidationRuleType.SELLER_AUTHORIZATION_RULE]
    )
    @DeleteMapping("/{id}")
    fun delete(@RequestHeader("Authorization") token: String, @PathVariable id: Long): ResponseEntity<Any> {
        productService.delete(id)
        return ResponseEntity.ok("successful")
    }
}