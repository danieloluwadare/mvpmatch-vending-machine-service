package com.mvpMatch.vendingmachineservice.model

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "products")
class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    var productName = ""

    var amountAvailable: Int = 0

    var cost: Int = 0

    @JoinColumn(name = "seller_id")
    @ManyToOne(fetch = FetchType.EAGER)
    lateinit var user: User

    var deletedAt : Date ?= null

    @CreationTimestamp
    private var createdAt: Date= Date()

    @UpdateTimestamp
    private var updatedAt: Date = Date()

}