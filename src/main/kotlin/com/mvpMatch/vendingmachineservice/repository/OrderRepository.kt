package com.mvpMatch.vendingmachineservice.repository

import com.mvpMatch.vendingmachineservice.model.Order
import com.mvpMatch.vendingmachineservice.model.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.Date
import java.util.Optional
@Repository
interface OrderRepository: JpaRepository<Order, Long> {
}