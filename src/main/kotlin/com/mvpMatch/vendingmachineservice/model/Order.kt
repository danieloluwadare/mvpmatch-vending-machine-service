package com.mvpMatch.vendingmachineservice.model

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "orders")
class Order {

    /**
     * buyer_id bigint(20) NOT NULL,
    product_id bigint(20) NOT NULL,
    amount_of_product int(10) DEFAULT 0,
    total_cost int(10) DEFAULT 0,
    reference varchar(255) DEFAULT NULL,
    status varchar(255) DEFAULT NULL,
    message varchar(255) DEFAULT NULL,
    created_at datetime DEFAULT CURRENT_TIMESTAMP,
    updated_at datetime DEFAULT CURRENT_TIMESTAMP,
    deleted_at datetime DEFAULT CURRENT_TIMESTAMP,
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @JoinColumn(name = "buyer_id")
    @ManyToOne(fetch = FetchType.EAGER)
    lateinit var buyer: User

    @JoinColumn(name = "product_id")
    @ManyToOne(fetch = FetchType.EAGER)
    lateinit var product: Product

    var amountOfProduct: Int = 0
    var totalCost: Int = 0
    var reference = ""
    var status = ""
    var message = ""
    var userChange = 0
    var userChangeDenomination = ""
    var refundedAmount: Int = 0

    var deletedAt : Date ?= null

    @CreationTimestamp
    private var createdAt: Date= Date()

    @UpdateTimestamp
    private var updatedAt: Date = Date()

}