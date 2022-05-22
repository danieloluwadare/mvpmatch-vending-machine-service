package com.mvpMatch.vendingmachineservice.authentication

import com.mvpMatch.vendingmachineservice.model.User
import com.mvpMatch.vendingmachineservice.model.dtos.JwtTokenDto
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Service
import java.util.*
@Service
class JwtTokenServiceImpl : TokenService {

    override fun generate(userUniqueIdentifier: String): JwtTokenDto {
        val expirationDate = 60 * 60 * 1000; // 1 hour
        val jwtAccessToken = Jwts.builder()
            .setIssuer(userUniqueIdentifier)
            .setExpiration(Date(System.currentTimeMillis() + expirationDate)) //
            .signWith(SignatureAlgorithm.HS512, "secret").compact()
        val jwtTokenDto = JwtTokenDto(jwtAccessToken,"bearer",expirationDate.toString());
        return jwtTokenDto;
    }

    override fun generate(user: User): JwtTokenDto {
        TODO("Not yet implemented")
    }

    override fun verify(accessToken : String) : String {
        val claims = Jwts.parser().setSigningKey("secret").parseClaimsJws(accessToken).body
        return claims.issuer
    }
}