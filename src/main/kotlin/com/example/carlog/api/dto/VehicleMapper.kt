package com.example.carlog.api.dto

import com.example.carlog.api.model.Vehicle

class VehicleMapper {
    companion object {
        fun mapToVehicleResponse(vehicle: Vehicle): VehicleResponse {
            return VehicleResponse(
                    id = vehicle.id,
                    brand = vehicle.brand,
                    model = vehicle.model,
                    year = vehicle.year,
                    vin = vehicle.vin,
                    horsepower = vehicle.horsepower,
                    torque = vehicle.torque,
            )
        }
    }
}
