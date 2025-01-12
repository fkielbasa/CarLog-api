package com.example.carlog.api.service

import com.example.carlog.api.dto.VehicleMapper
import com.example.carlog.api.dto.VehicleRequest
import com.example.carlog.api.dto.VehicleResponse
import com.example.carlog.api.exception.UserNotFoundException
import com.example.carlog.api.exception.VehicleNotFoundException
import com.example.carlog.api.model.Vehicle
import com.example.carlog.api.repository.UserRepository
import com.example.carlog.api.repository.VehicleRepository
import org.springframework.stereotype.Service

@Service
class VehicleService( private val vehicleRepository: VehicleRepository, private val userRepository: UserRepository) {
    fun getAllVehicles(): List<VehicleResponse> {
        return vehicleRepository.findAll().map { vehicle ->
            VehicleMapper.mapToVehicleResponse(vehicle)
        }
    }

    fun getVehiclesByUserId(userId: Long): List<VehicleResponse> {
        val vehicles = vehicleRepository.findAllByUserId(userId)
        if (vehicles.isEmpty()) {
            throw VehicleNotFoundException("No vehicles found for user with id $userId")
        }
        return vehicles.map { vehicle ->
            VehicleMapper.mapToVehicleResponse(vehicle)
        }
    }

    fun addVehicle(vehicle: VehicleRequest): VehicleResponse {
        val userId = vehicle.userId ?: throw IllegalArgumentException("User ID cannot be null");

        val vehicle = Vehicle(
                brand = vehicle.brand,
                model = vehicle.model,
                year = vehicle.year,
                vin = vehicle.vin,
                horsepower = vehicle.horsepower,
                torque = vehicle.torque,
                user = userRepository.findById(userId)
                        .orElseThrow { throw UserNotFoundException("User with id $userId not found") }
        )
        vehicleRepository.save(vehicle);
        return VehicleMapper.mapToVehicleResponse(vehicle);
    }
    fun updateVehicle(id: Long, vehicleRequest: VehicleRequest): VehicleResponse {

        val existingVehicle = vehicleRepository.findById(id).orElseThrow {
            VehicleNotFoundException("Vehicle with id $id not found")
        }

        vehicleRequest.brand?.let { existingVehicle.brand = it }
        vehicleRequest.model?.let { existingVehicle.model = it }
        vehicleRequest.year?.let { existingVehicle.year = it }
        vehicleRequest.vin?.let { existingVehicle.vin = it }
        vehicleRequest.horsepower?.let { existingVehicle.horsepower = it }
        vehicleRequest.torque?.let { existingVehicle.torque = it }

        vehicleRepository.save(existingVehicle)

        return VehicleMapper.mapToVehicleResponse(existingVehicle)
    }

    fun deleteVehicle(id: Long) {
        if (!vehicleRepository.existsById(id)) {
            throw VehicleNotFoundException("Vehicle with id $id not found")
        }
        vehicleRepository.deleteById(id)
    }
}
