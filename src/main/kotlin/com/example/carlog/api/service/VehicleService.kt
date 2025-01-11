package com.example.carlog.api.service

import com.example.carlog.api.dto.VehicleResponse
import com.example.carlog.api.exception.VehicleNotFoundException
import com.example.carlog.api.model.Vehicle
import com.example.carlog.api.repository.VehicleRepository
import org.springframework.stereotype.Service

@Service
class VehicleService( private val vehicleRepository: VehicleRepository) {
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

    fun addVehicle(vehicle: Vehicle): Vehicle {
        return vehicleRepository.save(vehicle)
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
