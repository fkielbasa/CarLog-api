package com.example.carlog.api.controller

import com.example.carlog.api.dto.ClientResponse
import com.example.carlog.api.dto.MechanicResponse
import com.example.carlog.api.model.User
import com.example.carlog.api.repository.UserRepository
import com.example.carlog.api.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/users")
class UserController(
        private val userRepository: UserRepository,
        private val userService: UserService
) {
    @GetMapping()
    fun getAllUsers(): List<User> {
        return userRepository.findAll()
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getUserById(@PathVariable id: Long): Any{
        return userService.getUserById(id);
    }
    @GetMapping("/vehicle/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getUserByVehicleId(@PathVariable id: Long): ClientResponse {
        return userService.getClientByVehicleId(id)
    }
    @GetMapping("/service/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getUserByServiceId(@PathVariable id: Long): MechanicResponse {
        return userService.getMechanicByServiceId(id)
    }
}
