package com.example.carlog.api.security

import com.example.carlog.api.dto.TokenResponse
import com.example.carlog.api.model.User
import com.example.carlog.api.repository.UserRepository
import com.example.carlog.api.service.JWTService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
        private val userRepository: UserRepository,
        private val passwordEncoder: PasswordEncoder,
        private val jwtService: JWTService,
        private val authenticationManager: AuthenticationManager
) {

    fun register(reguest: ReqisterRequest): TokenResponse {
//        if (userRepository.findByUsername(authRequest.login)) {
//            throw IllegalArgumentException("User with this login already exists.")
//        }

        val user =  User(
                login = reguest.login,
                password = passwordEncoder.encode(reguest.password),
                firstName = reguest.firstName,
                lastName = reguest.lastName,
                address = reguest.address,
                email = reguest.email,
                phoneNumber = reguest.phoneNumber
        )
        userRepository.save(user)

        return TokenResponse(jwtService.generateToken(user))
    }

    fun authenticate(user: AuthRequest): TokenResponse {
        authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(user.login, user.password)
        ).isAuthenticated
        val authenticatedUser = userRepository.findUserByLogin(user.login)
        return authenticatedUser?.let {
            TokenResponse(jwtService.generateToken(it))
        } ?: throw IllegalArgumentException("User not found")
    }
    fun isUserExists(login: String, email: String): Boolean {
        return userRepository.existsByLoginOrEmail(login, email);
    }
}
