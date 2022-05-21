package com.mvpMatch.vendingmachineservice.repository

import com.mvpMatch.vendingmachineservice.model.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.Date
import java.util.Optional
@Repository
interface ProductRepository: JpaRepository<Product, Long> {
    @Query("update Product p set p.deletedAt = :balance where p.id < :id")
    fun updateProductDeletedAt(deleteAt: Date, id: Long)
    fun findByIdAndDeletedAtIsNull(id: Long) : Optional<Product>
}