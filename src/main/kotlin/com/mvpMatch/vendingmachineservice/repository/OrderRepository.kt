package com.mvpMatch.vendingmachineservice.repository

import com.mvpMatch.vendingmachineservice.model.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.Date
import java.util.Optional
@Repository
interface OrderRepository: JpaRepository<Product, Long> {
    @Modifying
    @Query("update Product p set p.deletedAt = :deletedAt where p.id = :id")
    fun updateDeletedAt(@Param("deletedAt")deleteAt: Date, @Param("id") id: Long)
    fun findByIdAndDeletedAtIsNull(id: Long) : Optional<Product>

    fun findAllByDeletedAtIsNull() : List<Product>

}