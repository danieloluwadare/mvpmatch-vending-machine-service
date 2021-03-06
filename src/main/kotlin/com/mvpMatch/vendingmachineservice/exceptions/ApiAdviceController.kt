package com.mvpMatch.vendingmachineservice.exceptions

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.ObjectNode
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ApiAdviceController(private val mapper: ObjectMapper) {
    private val log = LoggerFactory.getLogger(javaClass)

    @ExceptionHandler(AuthenticationException::class)
    fun authenticationException(ex: AuthenticationException): ResponseEntity<JsonNode?>? {
        log.error("ExceptionHandler AuthenticatedException Exception >>> $ex ")
        val response: ObjectNode = mapper.createObjectNode()
            .putPOJO("error", "Invalid credential")
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response)
    }

    @ExceptionHandler(UserSessionActiveException::class)
    fun userSessionActiveException(ex: UserSessionActiveException): ResponseEntity<JsonNode?>? {
        log.error("ExceptionHandler UserSessionActiveException Exception >>> $ex ")
        val response: ObjectNode = mapper.createObjectNode()
            .putPOJO("error", "There is already an active session using your account")
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response)
    }

    @ExceptionHandler(AccessTokenAuthenticationException::class)
    fun accessTokenAuthenticationException(ex: AccessTokenAuthenticationException): ResponseEntity<JsonNode?>? {
        log.error("ExceptionHandler accessTokenAuthenticationException Exception >>> $ex ")
        val response: ObjectNode = mapper.createObjectNode()
            .putPOJO("error", "invalid access token")
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response)
    }

    @ExceptionHandler(UserRegistrationException::class)
    fun userRegistrationException(ex: UserRegistrationException): ResponseEntity<JsonNode?>? {

        log.error("ExceptionHandler userRegistrationException Exception >>> $ex ")
        val response: ObjectNode = mapper.createObjectNode()
            .putPOJO("error_description", ex.message)
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response)
    }

    @ExceptionHandler(ProductException::class)
    fun productException(ex: ProductException): ResponseEntity<JsonNode?>? {

        log.error("ExceptionHandler productException Exception >>> $ex ")
        val response: ObjectNode = mapper.createObjectNode()
            .putPOJO("error", ex.message)
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response)
    }

    @ExceptionHandler(UnAuthorizedUserException::class)
    fun unAuthorizedUserException(ex: UnAuthorizedUserException): ResponseEntity<JsonNode?>? {
        log.error("ExceptionHandler UnAuthorizedUserException Exception >>> $ex ")
        val response: ObjectNode = mapper.createObjectNode()
            .putPOJO("error", ex.message)
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response)
    }

    @ExceptionHandler(UserDepositException::class)
    fun userDepositException(ex: UserDepositException): ResponseEntity<JsonNode?>? {

        log.error("ExceptionHandler productException Exception >>> $ex ")
        val response: ObjectNode = mapper.createObjectNode()
            .putPOJO("error", ex.message)
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response)
    }

    @ExceptionHandler(OrderException::class)
    fun userDepositException(ex: OrderException): ResponseEntity<JsonNode?>? {

        log.error("ExceptionHandler productException Exception >>> $ex ")
        log.error("userChange ${ex.userChange.sum()}")
        val response: ObjectNode = mapper.createObjectNode()
            .putPOJO("error", ex.message)
            .putPOJO("refund", true)
            .putPOJO("change", ex.userChange)
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response)
    }

}