package com.example.carlog.api.service

import com.example.carlog.api.dto.ServiceEntryDto
import com.example.carlog.api.model.ServiceEntry
import com.example.carlog.api.repository.ServiceEntryRepository
import com.example.carlog.api.repository.VehicleRepository
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Service
class ServiceEntryService (
        private val serviceEntryRepository: ServiceEntryRepository,
        private val vehicleRepository: VehicleRepository,
) {

    fun addService(request: ServiceEntryDto): ServiceEntryDto {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val vehicleId = request.vehicleId ?: throw IllegalArgumentException("Vehicle id cannot be null")

        val serviceEntry = ServiceEntry(
            vehicle = vehicleRepository.findById(vehicleId).orElseThrow(),
            price = BigDecimal(request.price),
            mileage = request.mileage.toLong(),
            description = request.description,
            date = LocalDate.parse(request.date, formatter),
            mechanicId = request.mechanicId
        )
        serviceEntryRepository.save(serviceEntry);
        return request;
    }
}

