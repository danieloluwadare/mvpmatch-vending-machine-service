package com.mvpMatch.vendingmachineservice.authentication.validations.specificValidation

import com.mvpMatch.vendingmachineservice.enums.ComputedValue
import com.mvpMatch.vendingmachineservice.exceptions.UnAuthorizedUserException
import com.mvpMatch.vendingmachineservice.model.Product
import com.mvpMatch.vendingmachineservice.model.User
import com.mvpMatch.vendingmachineservice.service.ProductService
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.lang.reflect.Method
import java.lang.reflect.Parameter

class SellerSpecificValidationRuleImplTest{

    @Test
    fun `throw UnAuthorizedUserException when user id does not equal product seller_id`() {

        val user = User()
        user.id = 1

        val productSeller = User()
        productSeller.id = 2

        val product = Product()
        product.id=2
        product.user = productSeller

        val computedValue:HashMap<String, Any> = HashMap();
        computedValue[ComputedValue.USER_ENTITY.name]=user

        val parameter = mockk<Parameter>(){
            every { isAnnotationPresent(any()) } returns true
        }
        val method = mockk<Method>{
            every {parameters} returns arrayOf(parameter)
        }
        val arguments : Array<Any> = arrayOf(product.id, product.id)

        val productService = mockk<ProductService>{
            every { findById(any()) } returns product
        }
        val roleValidationRule = SellerSpecificValidationRuleImpl(productService)

        var exceptionThrown: Boolean = false
        var exception : Exception? = null
        try{
            roleValidationRule.preValidate(method,arguments,computedValue)
        }catch (ex: Exception){
            exception = ex
            exceptionThrown=true
            print("exception occurred")
        }

        assertTrue(exceptionThrown)
        assertInstanceOf(UnAuthorizedUserException::class.java, exception)
    }

    @Test
    fun `do not throw UnAuthorizedUserException when user id equal product seller_id`() {

        val user = User()
        user.id = 1

        val product = Product()
        product.id=2
        product.user = user

        val computedValue:HashMap<String, Any> = HashMap();
        computedValue[ComputedValue.USER_ENTITY.name]=user

        val parameter = mockk<Parameter>(){
            every { isAnnotationPresent(any()) } returns true
        }
        val method = mockk<Method>{
            every {parameters} returns arrayOf(parameter)
        }
        val arguments : Array<Any> = arrayOf(product.id, product.id)

        val productService = mockk<ProductService>{
            every { findById(any()) } returns product
        }
        val roleValidationRule = SellerSpecificValidationRuleImpl(productService)

        var exceptionThrown: Boolean = false
        try{
            roleValidationRule.preValidate(method,arguments,computedValue)
        }catch (ex: Exception){
            exceptionThrown=true
            print("exception occurred")
        }

        assertFalse(exceptionThrown)
    }
}