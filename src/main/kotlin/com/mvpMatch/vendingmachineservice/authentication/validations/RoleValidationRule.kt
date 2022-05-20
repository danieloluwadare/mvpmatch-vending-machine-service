package com.mvpMatch.vendingmachineservice.authentication.validations

import com.mvpMatch.vendingmachineservice.enums.RoleType

interface RoleValidationRule : ValidationRule {
    fun getType(): RoleType
}