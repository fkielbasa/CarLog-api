package com.example.carlog.api.controller

import com.example.carlog.api.dto.VehicleResponse
import com.example.carlog.api.model.Vehicle
import com.example.carlog.api.service.VehicleService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/vehicles")
class VehicleController( private val vehicleService: VehicleService) {
    @GetMapping
    fun getAllVehicles(): List<VehicleResponse> {
        return vehicleService.getAllVehicles()
    }

    @GetMapping("/{id}")
    fun getVehicleById(@PathVariable id: Long): Vehicle {
        return vehicleService.getVehicleById(id)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addVehicle(@RequestBody vehicle: Vehicle): Vehicle {
        return vehicleService.addVehicle(vehicle)
    }

    @PutMapping("/{id}")
    fun updateVehicle(@PathVariable id: Long, @RequestBody vehicle: Vehicle): Vehicle {
        return vehicleService.updateVehicle(id, vehicle)
    }

}
