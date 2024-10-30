package com.example.carlog.api.service

import com.example.carlog.api.model.User
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service

@Service
class UserService(val authenticationManager: AuthenticationManager) {

    fun verify(user: User): Boolean = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(user.username, user.password)
    ).isAuthenticated
}
