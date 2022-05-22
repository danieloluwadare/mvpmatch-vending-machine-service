package com.mvpMatch.vendingmachineservice.authentication.validations.specificValidation

import com.mvpMatch.vendingmachineservice.authentication.validations.ValidationRule
import com.mvpMatch.vendingmachineservice.enums.ValidationRuleType

interface SpecificValidationRule : ValidationRule {
    fun getType(): ValidationRuleType
}