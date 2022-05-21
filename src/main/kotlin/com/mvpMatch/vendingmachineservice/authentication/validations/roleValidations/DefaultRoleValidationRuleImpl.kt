package com.mvpMatch.vendingmachineservice.authentication.validations.roleValidations

import com.mvpMatch.vendingmachineservice.enums.RoleType
import org.springframework.stereotype.Service

@Service
class DefaultRoleValidationRuleImpl : RoleValidationRule {
    override fun getType(): RoleType {
        return RoleType.DEFAULT
    }

}