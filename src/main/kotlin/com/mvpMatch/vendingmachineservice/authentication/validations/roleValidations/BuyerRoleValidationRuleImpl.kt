package com.mvpMatch.vendingmachineservice.authentication.validations.roleValidations

import com.mvpMatch.vendingmachineservice.enums.ComputedValue
import com.mvpMatch.vendingmachineservice.enums.RoleType
import com.mvpMatch.vendingmachineservice.exceptions.UnAuthorizedUserException
import com.mvpMatch.vendingmachineservice.model.User
import org.springframework.stereotype.Service
import java.lang.reflect.Method

@Service
class BuyerRoleValidationRuleImpl : RoleValidationRule {
    override fun getType(): RoleType {
        return RoleType.BUYER
    }

    override fun preValidate(method: Method, arguments: Array<Any>, computedValues: HashMap<String, Any>) {
        val user  = computedValues[ComputedValue.USER_ENTITY.name] as User
        if(!user.role.equals(RoleType.BUYER.value, true))
            throw UnAuthorizedUserException("operation forbidden","")
    }
}