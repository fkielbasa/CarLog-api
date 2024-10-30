package com.example.carlog.api.service

import com.example.carlog.api.model.User
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.security.Key
import java.util.*
import kotlin.collections.HashMap

@Service
class JWTService(
        @Value("\${token.expiration.time}") private val EXPIRATION_TIME: Long,
        @Value("\${token.secret_key}") private val SECRET_KEY: String
) {
    private val claims: HashMap<String, Any> = HashMap()

    fun generateToken(user: User): String {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.username)
                .claim("id",user.id)
                .claim("username",user.username)
                .claim("Name", user.firstName + " " + user.lastName)
                .setIssuedAt(Date(System.currentTimeMillis()))
                .setExpiration(Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getKey())
                .compact()
    }
    private fun getKey(): Key {
        val keyBytes = Decoders.BASE64.decode(SECRET_KEY)
        return Keys.hmacShaKeyFor(keyBytes)
    }
}
