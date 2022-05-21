package com.mvpMatch.vendingmachineservice.authentication.validations

import com.mvpMatch.vendingmachineservice.model.User

interface MvpSecurityContext {
    fun setPrincipalUser(user: User)
    fun getPrincipalUser(): User
}