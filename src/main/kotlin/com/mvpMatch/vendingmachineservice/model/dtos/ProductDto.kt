package com.mvpMatch.vendingmachineservice.model.dtos

import com.mvpMatch.vendingmachineservice.authentication.validations.MvpSecurityContext
import com.mvpMatch.vendingmachineservice.model.User
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

class ProductDto : MvpSecurityContext {
    @NotBlank(message = "productName is required")
    var productName = ""
    @NotNull(message = "amountAvailable is required")
    var amountAvailable: Int = 0
    @NotNull(message = "cost is required")
    var cost: Int = 0

    private lateinit var principalSecurityUser : User

    override fun setPrincipalUser(user: User) {
        this.principalSecurityUser=user;
    }
    override fun getPrincipalUser(): User {
        return principalSecurityUser
    }
}