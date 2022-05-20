package com.mvpMatch.vendingmachineservice.repository

import com.mvpMatch.vendingmachineservice.model.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository: JpaRepository<Product, Long> {
}