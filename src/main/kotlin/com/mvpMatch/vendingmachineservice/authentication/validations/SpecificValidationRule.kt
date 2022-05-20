package com.mvpMatch.vendingmachineservice.authentication.validations

import com.mvpMatch.vendingmachineservice.enums.ValidationRuleType

interface SpecificValidationRule : ValidationRule {
    fun getType(): ValidationRuleType
}