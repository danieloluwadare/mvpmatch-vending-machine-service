package com.mvpMatch.vendingmachineservice.service

import com.mvpMatch.vendingmachineservice.model.User
import com.mvpMatch.vendingmachineservice.model.dtos.JwtTokenDto
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Service
import java.util.*
@Service
class JwtTokenGeneratorImpl : TokenGenerator {

    override fun generate(userUniqueIdentifier: String): JwtTokenDto {
        val expirationDate = 60 * 1 * 1000; //
        val jwtAccessToken = Jwts.builder()
            .setIssuer(userUniqueIdentifier)
            .setExpiration(Date(System.currentTimeMillis() + expirationDate)) // 1 day
            .signWith(SignatureAlgorithm.HS512, "secret").compact()
        val jwtTokenDto = JwtTokenDto(jwtAccessToken,"bearer",expirationDate.toString());
        return jwtTokenDto;
    }

    override fun generate(user: User): JwtTokenDto {
        TODO("Not yet implemented")
    }
}