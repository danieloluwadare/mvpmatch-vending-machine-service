package com.mvpMatch.vendingmachineservice.model

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.math.BigDecimal
import javax.persistence.*

@Entity
@Table(name = "users")
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    var username = ""

    var deposit: BigDecimal? = null

    var role = ""

    var password = ""
        @JsonIgnore
        get() = field
        set(value) {
            val passwordEncoder = BCryptPasswordEncoder()
            field = passwordEncoder.encode(value)
        }
    fun comparePassword(password: String): Boolean {
        return BCryptPasswordEncoder().matches(password, this.password)
    }
}