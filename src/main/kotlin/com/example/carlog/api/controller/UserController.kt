package com.example.carlog.api.controller

import com.example.carlog.api.model.User
import com.example.carlog.api.repository.UserRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/users")
class UserController(
        private val userRepository: UserRepository
) {
    @GetMapping()
    fun getAllUsers(): List<User> {
        return userRepository.findAll()
    }
}
