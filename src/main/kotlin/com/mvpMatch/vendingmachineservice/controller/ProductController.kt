package com.mvpMatch.vendingmachineservice.controller

import com.mvpMatch.vendingmachineservice.annotations.EnableMvpSecurity
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
@RequestMapping("api/v1/product","v1/product")
class ProductController(private val productService: ProductService, private val userRepository: UserRepository) {
    private val log = LoggerFactory.getLogger(javaClass)

    @EnableMvpSecurity
    @PostMapping("")
    fun create(@RequestHeader("Authorization") token : String,@Valid @RequestBody productDto : ProductDto):ResponseEntity<Product> {
        productDto.setPrincipalUser(getUser())
        return ResponseEntity.ok(productService.create(productDto))
    }
    @GetMapping("/{id}")
    fun get(@PathVariable id: Long ):ResponseEntity<Product> {
        return ResponseEntity.ok(productService.get(id))
    }
    @GetMapping("")
    fun getAll():ResponseEntity<List<Product>> {
        return ResponseEntity.ok(productService.getAll())
    }
    @PutMapping("/{id}")
    fun update(@Valid @RequestBody productDto : ProductDto,@PathVariable id: Long):ResponseEntity<Product> {
        return ResponseEntity.ok(productService.update(productDto,id))
    }
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long ):ResponseEntity<Any> {
        productService.delete(id)
        return ResponseEntity.ok("successful")
    }
    fun getUser() :User{
        return userRepository.findAll()[0]
    }
}