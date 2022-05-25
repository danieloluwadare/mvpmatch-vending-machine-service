package com.mvpMatch.vendingmachineservice.authentication.validations.roleValidations

import com.mvpMatch.vendingmachineservice.enums.ComputedValue
import com.mvpMatch.vendingmachineservice.enums.RoleType
import com.mvpMatch.vendingmachineservice.exceptions.UnAuthorizedUserException
import com.mvpMatch.vendingmachineservice.model.User
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.lang.reflect.Method

@Service
class SellerRoleValidationRuleImpl : RoleValidationRule {
    private val log = LoggerFactory.getLogger(javaClass)

    override fun getType(): RoleType {
        return RoleType.SELLER
    }

    override fun preValidate(method: Method, arguments: Array<Any>, computedValues: HashMap<String, Any>) {
        log.info("SellerRoleValidationRuleImpl")
        val user = computedValues[ComputedValue.USER_ENTITY.name] as User
        if (!user.role.equals(RoleType.SELLER.value, true))
            throw UnAuthorizedUserException("operation forbidden", "")
    }
}