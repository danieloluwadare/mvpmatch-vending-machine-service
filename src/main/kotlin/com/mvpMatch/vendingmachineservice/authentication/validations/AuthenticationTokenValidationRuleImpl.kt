package com.mvpMatch.vendingmachineservice.authentication.validations

import com.mvpMatch.vendingmachineservice.authentication.TokenService
import com.mvpMatch.vendingmachineservice.enums.ComputedValue
import com.mvpMatch.vendingmachineservice.exceptions.AccessTokenAuthenticationException
import com.mvpMatch.vendingmachineservice.exceptions.UserNotFoundException
import com.mvpMatch.vendingmachineservice.service.UserService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import java.lang.reflect.Method

@Service
class AuthenticationTokenValidationRuleImpl(
    private val tokenService: TokenService,
    private val userService: UserService
) : ValidationRule {
    private val log = LoggerFactory.getLogger(javaClass)

    override fun preValidate(method: Method, arguments: Array<Any>, computedValues: HashMap<String, Any>) {
        val bearerToken = StringBuilder()
        val parameters = method.parameters
        for (i in parameters.indices) {
            val parameter = parameters[i]
            if (parameter.isAnnotationPresent(RequestHeader::class.java)) {
                bearerToken.append(arguments[i] as String?)
            }
            if (parameter.isAnnotationPresent(RequestBody::class.java)) {
                val requestBody = arguments[i]
                if (requestBody is MvpSecurityContext) {
                    computedValues[ComputedValue.MVP_SECURITY_CONTEXT_REQUEST.name] = requestBody
                }
            }
        }
        log.info("Bearer Token ==> $bearerToken")
        val accessToken = bearerToken.split(" ")[1]
        val userIdentifier: String
        try {
            userIdentifier = tokenService.verify(accessToken)
            val user = userService.findByUsername(userIdentifier) ?: throw UserNotFoundException("User not found", "")
            computedValues[ComputedValue.USER_ENTITY.name] = user
        } catch (exception: Exception) {
            throw AccessTokenAuthenticationException("access token cannot be validated", "")
        }

    }

}