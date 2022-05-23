package com.mvpMatch.vendingmachineservice.model.dtos

import com.mvpMatch.vendingmachineservice.authentication.validations.MvpSecurityContext
import com.mvpMatch.vendingmachineservice.model.User
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

class OrderDto : MvpSecurityContext{
    @NotNull(message = "amount is required")
    var productId = 0
    @NotBlank(message = "amountOfProduct is required")
    var amountOfProduct = 0

    private lateinit var principalSecurityUser : User

    override fun setPrincipalUser(user: User) {
        this.principalSecurityUser=user;
    }
    override fun getPrincipalUser(): User {
        return principalSecurityUser
    }
}