package com.mvpMatch.vendingmachineservice.model

import javax.persistence.*

@Entity
@Table(name = "products")
class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    var productName = ""

    var amountAvailable: Int = 0

    var cost: Int = 0

    @JoinColumn(name = "seller_id")
    @ManyToOne(fetch = FetchType.EAGER)
    var user: User? = null
}