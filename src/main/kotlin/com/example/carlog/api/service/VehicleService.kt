package com.example.carlog.api.service

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
            VehicleResponse(
                    vehicle.id,
                    vehicle.user.id,
                    vehicle.brand,
                    vehicle.model,
                    vehicle.year,
                    vehicle.vin,
                    vehicle.horsepower,
                    vehicle.torque,
                    vehicle.services)  }
    }

    fun getVehiclesByUserId(userId: Long): List<VehicleResponse> {
        val vehicles = vehicleRepository.findAllByUserId(userId)
        if (vehicles.isEmpty()) {
            throw VehicleNotFoundException("No vehicles found for user with id $userId")
        }
        return vehicles.map { vehicle ->
            VehicleResponse(
                    vehicle.id,
                    vehicle.user.id,
                    vehicle.brand,
                    vehicle.model,
                    vehicle.year,
                    vehicle.vin,
                    vehicle.horsepower,
                    vehicle.torque,
                    vehicle.services
            )
        }
    }

    fun addVehicle(vehicle: VehicleRequest): Vehicle {
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
        return vehicle;
    }

    fun updateVehicle(id: Long, vehicle: Vehicle): Vehicle {
        if (!vehicleRepository.existsById(id)) {
            throw VehicleNotFoundException("Vehicle with id $id not found")
        }
        vehicle.id = id
        return vehicleRepository.save(vehicle)
    }

    fun deleteVehicle(id: Long) {
        if (!vehicleRepository.existsById(id)) {
            throw VehicleNotFoundException("Vehicle with id $id not found")
        }
        vehicleRepository.deleteById(id)
    }
}
