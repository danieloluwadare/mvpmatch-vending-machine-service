package com.mvpMatch.vendingmachineservice.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.mvpMatch.vendingmachineservice.model.User
import com.mvpMatch.vendingmachineservice.model.dtos.JwtTokenDto
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.web.client.RestTemplate

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest{
    @Value("\${local.server.port}")
    val port :Int? =2000

    val rest =  RestTemplate()

    val mapper = ObjectMapper()

    @Test
    fun `test deposit endpoint`(){
        val token = getAuthenticatedUser("daniel","password","buyer")
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        headers["Authorization"] = "Bearer $token"
        val req = mapper.createObjectNode()
            .putPOJO("amount",50)

        val url = "http://localhost:${port}/api/v1/user/deposit"
        println(url)
        val user = rest
            .postForObject(url,HttpEntity(req, headers), User::class.java,HashMap<String,Any>())

        println("deposit  ==>${user?.deposit}")
        println("username  ==>${user?.username}")

        assertEquals(50, user?.deposit)
        assertEquals("daniel", user?.username)
    }

    private fun getAuthenticatedUser(username:String,password:String,role:String): String{

        //register a user
        var headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        var req = mapper.createObjectNode()
            .putPOJO("username",username)
            .putPOJO("password", password)
            .putPOJO("role",role)
        var url = "http://localhost:${port}/api/v1/user"
        println(url)
        val user = rest
            .postForObject(url,HttpEntity(req, headers), User::class.java,HashMap<String,Any>())

        println("username ==>${user?.username}")
        println("password ==>${user?.role}")
        println("deposit ==>${user?.deposit}")


        headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        req = mapper.createObjectNode()
            .putPOJO("username",username)
            .putPOJO("password", password)

        url = "http://localhost:${port}/api/v1/oauth/token"
        println(url)
        val jwtTokenDto = rest
            .postForObject(url,HttpEntity(req, headers), JwtTokenDto::class.java,HashMap<String,Any>())

        println("accessToken ==>${jwtTokenDto?.access_token}")
        assertNotNull(jwtTokenDto?.access_token)
        return jwtTokenDto?.access_token!!
    }

}