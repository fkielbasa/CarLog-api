package com.example.carlog.api.service

import com.example.carlog.api.model.User
import com.example.carlog.api.repository.UserRepository
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.security.Key
import java.util.*
import kotlin.collections.HashMap
import kotlin.reflect.KFunction1

@Service
class JWTService(
        private val userRepository: UserRepository,
        @Value("\${token.expiration.time}") private val EXPIRATION_TIME: Long,
        @Value("\${token.secret_key}") private val SECRET_KEY: String,
) {
    private val claims: HashMap<String, Any> = HashMap()
    fun generateToken(user: User): String {
        val foundUser: User = userRepository.findByUsername(user.username) ?: throw UsernameNotFoundException("User not found")
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(foundUser.username)
                .claim("id",foundUser.id)
                .claim("username",foundUser.username)
                .claim("Name", foundUser.firstName + " " + foundUser.lastName)
                .setIssuedAt(Date(System.currentTimeMillis()))
                .setExpiration(Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getKey())
                .compact()
    }
    private fun getKey(): Key {
        val keyBytes = Decoders.BASE64.decode(SECRET_KEY)
        return Keys.hmacShaKeyFor(keyBytes)
    }

    fun extractUserName(token: String): String {
        return extractClaim(token, Claims::getSubject)
    }

    private fun <T> extractClaim(token: String, claimResolver: (Claims) -> T): T {
        val claims: Claims = extractAllClaims(token)
        return claimResolver(claims)
    }

    private fun extractAllClaims(token: String): Claims {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .body
    }
    fun isTokenValid(token: String, userDetails: UserDetails): Boolean {
        val userName = extractUserName(token)
        return (userName.equals(userDetails.username)) && !isTokenExpired(token)
    }

    private fun isTokenExpired(token: String): Boolean {
        return extractExpiration(token).before(Date())
    }

    private fun extractExpiration(token: String): Date {
        return extractClaim(token, Claims::getExpiration)
    }
}
