package com.mvpMatch.vendingmachineservice.authentication.validations.roleValidations

import com.mvpMatch.vendingmachineservice.enums.ComputedValue
import com.mvpMatch.vendingmachineservice.exceptions.UnAuthorizedUserException
import com.mvpMatch.vendingmachineservice.model.User
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.lang.reflect.Method

class SellerRoleValidationRuleImplTest{
    @Test
    fun `throw UnAuthorizedUserException when user does not have seller role`() {

        val user = User()
        user.password="password"
        user.username="mvpmatch"
        user.role ="buyer"

        val computedValue:HashMap<String, Any> = HashMap();
        computedValue[ComputedValue.USER_ENTITY.name]=user

        val method = mockk<Method>()
        var arguments : Array<Any> = arrayOf(1,10,4,6,15)

        val roleValidationRule = SellerRoleValidationRuleImpl()

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
    fun `dont throw UnAuthorizedUserException when user does have seller role`() {

        val user = User()
        user.password="password"
        user.username="mvpmatch"
        user.role ="seller"

        val computedValue:HashMap<String, Any> = HashMap();
        computedValue[ComputedValue.USER_ENTITY.name]=user

        val method = mockk<Method>()
        var arguments : Array<Any> = arrayOf(1,10,4,6,15)

        val roleValidationRule = SellerRoleValidationRuleImpl()

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