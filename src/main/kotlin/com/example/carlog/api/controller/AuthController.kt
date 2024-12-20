package com.example.carlog.api.controller

import com.example.carlog.api.security.AuthRequest
import com.example.carlog.api.security.ReqisterRequest
import com.example.carlog.api.security.AuthService
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
    fun register(
            @RequestBody request: ReqisterRequest
    ): ResponseEntity<String> {
        return ResponseEntity.ok<String>(authService.register(request))
    }
    @PostMapping("/login")
    fun authenticate(
            @RequestBody user: AuthRequest)
    : String {
        System.out.println("work")
        return authService.authenticate(user)
    }
}
