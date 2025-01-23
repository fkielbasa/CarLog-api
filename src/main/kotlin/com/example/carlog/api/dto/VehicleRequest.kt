package com.example.carlog.api.dto


data class VehicleRequest(val userId: Long?, val brand: String, val model: String, val year: String, val vin: String, val horsepower: String, val torque: String, val mileage: String) {
}
