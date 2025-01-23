package com.example.carlog.api.controller

import com.example.carlog.api.dto.VehicleRequest
import com.example.carlog.api.dto.VehicleResponse
import com.example.carlog.api.model.Vehicle
import com.example.carlog.api.service.VehicleService
import org.apache.coyote.Response
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/vehicles")
class VehicleController( private val vehicleService: VehicleService) {
    @GetMapping
    fun getAllVehicles(): List<VehicleResponse> {
        return vehicleService.getAllVehicles()
    }

    @GetMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getVehicleByUserId(@PathVariable id: Long): List<VehicleResponse> {
        return vehicleService.getVehiclesByUserId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addVehicle(@RequestBody vehicle: VehicleRequest): VehicleResponse {
        return vehicleService.addVehicle(vehicle)
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun updateVehicle(@PathVariable id: Long, @RequestBody vehicle: VehicleRequest): VehicleResponse {
        return vehicleService.updateVehicle(id, vehicle)
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun deleteVehicle(@PathVariable id: Long) {
        return vehicleService.deleteVehicle(id)
    }
//    @GetMapping("/{vin}")
//    @ResponseStatus(HttpStatus.OK)
//    fun findVehicleByVinNumber(@PathVariable vin: String): VehicleResponse {
//        return vehicleService.findVehicleByVin(vin);
//    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getVehicleById(@PathVariable id: Long): VehicleResponse {
        return vehicleService.getVehicleById(id);
    }
}
