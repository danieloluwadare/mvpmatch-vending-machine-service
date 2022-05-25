package com.mvpMatch.vendingmachineservice.authentication.validations.specificValidation

import com.mvpMatch.vendingmachineservice.enums.ComputedValue
import com.mvpMatch.vendingmachineservice.enums.ValidationRuleType
import com.mvpMatch.vendingmachineservice.exceptions.UnAuthorizedUserException
import com.mvpMatch.vendingmachineservice.model.User
import com.mvpMatch.vendingmachineservice.service.ProductService
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PathVariable
import java.lang.reflect.Method

@Service
class SellerSpecificValidationRuleImpl(private val productService: ProductService) : SpecificValidationRule {
    override fun getType(): ValidationRuleType {
        return ValidationRuleType.SELLER_AUTHORIZATION_RULE
    }

    override fun preValidate(method: Method, arguments: Array<Any>, computedValues: HashMap<String, Any>) {
        var productId: Long = 0
        val parameters = method.parameters
        for (i in parameters.indices) {
            val parameter = parameters[i]
            if (parameter.isAnnotationPresent(PathVariable::class.java)) {
                productId = arguments[i] as Long
            }
        }
        val product = productService.findById(productId)
        val user = computedValues[ComputedValue.USER_ENTITY.name] as User
        if (user.id != product.seller.id)
            throw UnAuthorizedUserException("operation forbidden", "")
    }
}