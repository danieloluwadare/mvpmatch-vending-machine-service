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
class ApiAdviceController(private val mapper : ObjectMapper) {
    private val log = LoggerFactory.getLogger(javaClass)

    @ExceptionHandler(AuthenticationException::class)
    fun authenticationException(ex: AuthenticationException): ResponseEntity<JsonNode?>? {
        /**
         * {
            "error": "invalid_grant",
            "error_description": "Bad credentials"
            }
         */
        log.error("ExceptionHandler AuthenticatedException Exception >>> $ex ")
        val response: ObjectNode = mapper.createObjectNode()
            .putPOJO("error", "invalid_credential")
            .putPOJO("error_description", "Bad credentials")
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response)
    }

    @ExceptionHandler(AccessTokenAuthenticationException::class)
    fun accessTokenAuthenticationException(ex: AccessTokenAuthenticationException): ResponseEntity<JsonNode?>? {
        /**
         * {
        "error": "invalid_grant",
        "error_description": "Bad credentials"
        }
         */
        log.error("ExceptionHandler accessTokenAuthenticationException Exception >>> $ex ")
        val response: ObjectNode = mapper.createObjectNode()
            .putPOJO("error", "invalid access token")
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response)
    }

    @ExceptionHandler(UserRegistrationException::class)
    fun userRegistrationException(ex: UserRegistrationException): ResponseEntity<JsonNode?>? {

        log.error("ExceptionHandler userRegistrationException Exception >>> $ex ")
        val response: ObjectNode = mapper.createObjectNode()
            .putPOJO("error", "invalid_credential")
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
}