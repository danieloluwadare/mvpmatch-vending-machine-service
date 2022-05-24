package com.mvpMatch.vendingmachineservice.model.dtos

import com.mvpMatch.vendingmachineservice.authentication.validations.MvpSecurityContext
import com.mvpMatch.vendingmachineservice.model.User
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

class ResetDto : MvpSecurityContext{
//    @NotNull(message = "user id is required")
//    var id : Long= 0
    private lateinit var principalSecurityUser : User

    override fun setPrincipalUser(user: User) {
        this.principalSecurityUser=user;
    }
    override fun getPrincipalUser(): User {
        return principalSecurityUser
    }
}