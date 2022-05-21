package com.mvpMatch.vendingmachineservice.aspect

import com.mvpMatch.vendingmachineservice.annotations.EnableMvpSecurity
import com.mvpMatch.vendingmachineservice.authentication.validations.AuthenticationTokenValidationRuleImpl
import com.mvpMatch.vendingmachineservice.authentication.validations.MvpSecurityContext
import com.mvpMatch.vendingmachineservice.authentication.validations.ValidationRule
import com.mvpMatch.vendingmachineservice.enums.ComputedValue
import com.mvpMatch.vendingmachineservice.model.User
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.reflect.MethodSignature
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Configuration

@Aspect
@Configuration
class EnableMvpSecurityAspect(@Qualifier("authenticationTokenValidationRuleImpl")private val authenticationTokenValidationRule: ValidationRule) {
    private val log = LoggerFactory.getLogger(javaClass)

    @Around("@annotation(com.mvpMatch.vendingmachineservice.annotations.EnableMvpSecurity)")
    @Throws(Throwable::class)
    fun around(joinPoint: ProceedingJoinPoint): Any {
        log.info("EnableMvpSecurityAspect")

        val computedValues: HashMap<String, Any> = HashMap()
        val arguments = joinPoint.args
        val signature = joinPoint.signature as MethodSignature
        val method = signature.method
        val enableMvpSecurity = method.getAnnotation(EnableMvpSecurity::class.java)
        authenticationTokenValidationRule.preValidate(method,arguments,computedValues)
        if(computedValues.containsValue(ComputedValue.MVP_SECURITY_CONTEXT_REQUEST.name)){
            val mvpSecurityContext  = computedValues[ComputedValue.MVP_SECURITY_CONTEXT_REQUEST.name] as MvpSecurityContext
            mvpSecurityContext.setPrincipalUser(computedValues[ComputedValue.USER_ENTITY.name] as User)
        }
//        enableMvpSecurity.hasAnyAuthority
        log.info("$computedValues")
        return joinPoint.proceed()
    }
}