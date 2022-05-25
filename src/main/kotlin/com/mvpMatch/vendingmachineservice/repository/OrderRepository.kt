package com.mvpMatch.vendingmachineservice.repository

import com.mvpMatch.vendingmachineservice.model.Order
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository : JpaRepository<Order, Long>