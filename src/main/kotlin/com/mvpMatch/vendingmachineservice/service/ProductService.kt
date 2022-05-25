package com.mvpMatch.vendingmachineservice.service

import com.mvpMatch.vendingmachineservice.model.Product
import com.mvpMatch.vendingmachineservice.model.dtos.ProductDto

interface ProductService {
    fun create(productDto: ProductDto): Product
    fun findById(id: Long): Product
    fun findAll(): List<Product>
    fun update(productDto: ProductDto, id: Long): Product
    fun delete(id: Long)
}