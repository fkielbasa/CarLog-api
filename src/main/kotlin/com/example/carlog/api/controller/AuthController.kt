package com.example.carlog.api.controller

import com.example.carlog.api.dto.TokenResponse
import com.example.carlog.api.security.AuthRequest
import com.example.carlog.api.security.ReqisterRequest
import com.example.carlog.api.security.AuthService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/auth")
class AuthController(
        private val authService: AuthService
) {

    @PostMapping("/register")
    fun register(@RequestBody request: ReqisterRequest): ResponseEntity<Any> {
        return if (authService.isUserExists(request.login, request.email)) {
            ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists.")
        } else {
            ResponseEntity.ok(authService.register(request))
        }
    }
    @PostMapping("/login")
    fun authenticate(
            @RequestBody user: AuthRequest)
    : ResponseEntity<TokenResponse> {
        return ResponseEntity.ok(authService.authenticate(user))
    }
}
