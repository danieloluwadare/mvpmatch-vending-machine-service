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

//    @ExceptionHandler(MethodArgumentNotValidException::class)
//    fun userUnAuthenticatedException(ex: MethodArgumentNotValidException): ResponseEntity<JsonNode?>? {
//        log.error("ExceptionHandler userUnAuthenticatedException Exception >>> $ex ")
//        val response: ObjectNode = mapper.createObjectNode().putPOJO("data", ex.message)
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response)
//    }

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
}