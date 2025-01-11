package com.example.carlog.api.dto

import com.example.carlog.api.model.ServiceEntry

data class VehicleRequest(val userId: Long?, val brand: String, val model: String, val year: String, val vin: String, val horsepower: String, val torque: String) {
}
