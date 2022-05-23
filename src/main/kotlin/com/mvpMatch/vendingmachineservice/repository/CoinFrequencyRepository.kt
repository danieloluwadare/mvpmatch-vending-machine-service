package com.mvpMatch.vendingmachineservice.repository

import com.mvpMatch.vendingmachineservice.model.CoinFrequency
import com.mvpMatch.vendingmachineservice.model.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CoinFrequencyRepository: JpaRepository<CoinFrequency, Long> {
    fun findByValueAndDeletedAtIsNull(id: Int) : CoinFrequency
}