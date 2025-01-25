package com.example.carlog.api.service

import com.example.carlog.api.dto.ClientResponse
import com.example.carlog.api.dto.MechanicResponse
import com.example.carlog.api.model.enums.Role
import com.example.carlog.api.dto.mapper.UserMapper
import com.example.carlog.api.exception.NotFoundException
import com.example.carlog.api.repository.ServiceEntryRepository
import com.example.carlog.api.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
        private val userRepository: UserRepository,
        private val serviceEntryRepository: ServiceEntryRepository
) {

    fun getClientByVehicleId(vehicleId: Long): ClientResponse {
        val user = userRepository.findUserByVehicles_Id(vehicleId)

        return user.filter{ it.role == Role.CLIENT }
                .map { UserMapper.mapToClientResponse(it) }
                .orElseThrow { NotFoundException("User not found or not a client.") }
    }
    fun getMechanicByServiceId(serviceId: Long): MechanicResponse {
        val serviceEntry = serviceEntryRepository.findById(serviceId).orElseThrow {
            NotFoundException("ServiceEntry not found")
        }

        return userRepository.findById(serviceEntry.mechanicId).filter{ it.role == Role.MECHANIC }
                .map { UserMapper.mapToMechanicResponse(it) }
                .orElseThrow{ NotFoundException("User not found or not a mechanic")}
    }
    fun getUserById(id: Long): Any {
        val user = userRepository.findById(id).orElseThrow {
            NotFoundException("User not found")
        }

        return when (user.role) {
            Role.MECHANIC -> {
                MechanicResponse(
                        firstName = user.firstName,
                        lastName = user.lastName,
                        email = user.email,
                        address = user.address,
                        phoneNumber = user.phoneNumber,
                        workshop = user.workshopName
                )
            }
            Role.CLIENT -> {
                ClientResponse(
                        firstName = user.firstName,
                        lastName = user.lastName,
                        email = user.email,
                        phoneNumber = user.phoneNumber,
                        address = user.address
                )
            }
        }
    }
}
