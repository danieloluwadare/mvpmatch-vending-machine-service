package com.mvpMatch.vendingmachineservice.model

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "coin_frequencies")
class CoinFrequency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0
    var value : Int = 0
    var frequency : Int = 0
    var deletedAt : Date ?= null
    @CreationTimestamp
    private var createdAt: Date= Date()
    @UpdateTimestamp
    private var updatedAt: Date = Date()

}