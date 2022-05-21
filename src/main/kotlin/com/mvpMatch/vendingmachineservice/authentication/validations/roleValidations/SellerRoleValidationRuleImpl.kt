package com.mvpMatch.vendingmachineservice.authentication.validations.roleValidations

import com.mvpMatch.vendingmachineservice.authentication.validations.MvpSecurityContext
import com.mvpMatch.vendingmachineservice.enums.ComputedValue
import com.mvpMatch.vendingmachineservice.enums.RoleType
import com.mvpMatch.vendingmachineservice.exceptions.UnAuthorizedUserException
import com.mvpMatch.vendingmachineservice.model.User
import org.springframework.stereotype.Service
import java.lang.reflect.Method

@Service
class SellerRoleValidationRuleImpl : RoleValidationRule {
    override fun getType(): RoleType {
        return RoleType.SELLER
    }

    override fun preValidate(method: Method, arguments: Array<Any>, computedValues: HashMap<String, Any>) {
        val user  = computedValues[ComputedValue.USER_ENTITY.name] as User
        if(!user.role.equals(RoleType.SELLER.value, true))
            throw UnAuthorizedUserException("operation forbidden","")
    }
}