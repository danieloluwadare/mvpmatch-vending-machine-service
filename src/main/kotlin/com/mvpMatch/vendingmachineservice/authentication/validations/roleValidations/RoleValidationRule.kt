package com.mvpMatch.vendingmachineservice.authentication.validations.roleValidations

import com.mvpMatch.vendingmachineservice.authentication.validations.ValidationRule
import com.mvpMatch.vendingmachineservice.enums.RoleType

interface RoleValidationRule : ValidationRule {
    fun getType(): RoleType
}