package com.example.carlog.api.repository

import com.example.carlog.api.model.Vehicle
import org.springframework.data.jpa.repository.JpaRepository

interface VehicleRepository: JpaRepository<Vehicle, Long> {
    fun findAllByUserId(userId: Long): List<Vehicle>
}
