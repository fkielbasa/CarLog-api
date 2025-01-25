package com.example.carlog.api.dto

data class ServiceEntryDto(val vehicleId: Long, val mileage: String, val date: String, val description: String, val price: String, val mechanicId: Long, val vehicle: String?, val workshop: String?)
