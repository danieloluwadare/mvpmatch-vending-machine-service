package com.mvpMatch.vendingmachineservice.repository

import com.mvpMatch.vendingmachineservice.model.CoinFrequency
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CoinFrequencyRepository: JpaRepository<CoinFrequency, Long> {
}