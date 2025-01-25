package com.example.carlog.api.service

import com.example.carlog.api.dto.ServiceEntryDto
import com.example.carlog.api.dto.mapper.ServiceMapper
import com.example.carlog.api.exception.NotFoundException
import com.example.carlog.api.model.ServiceEntry
import com.example.carlog.api.repository.ServiceEntryRepository
import com.example.carlog.api.repository.UserRepository
import com.example.carlog.api.repository.VehicleRepository
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@Service
class ServiceEntryService (
        private val serviceEntryRepository: ServiceEntryRepository,
        private val vehicleRepository: VehicleRepository,
        private val userRepository: UserRepository
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
            mechanicId = request.mechanicId,
        )
        serviceEntryRepository.save(serviceEntry);
        return request;
    }
    fun getServicesByVehicleId(id: Long): List<ServiceEntryDto>{
        val services = serviceEntryRepository.getServiceEntriesByVehicleId(id)
                .orElseThrow { NotFoundException("Services not found") }

        return services.map { serviceEntry ->
            val mechanic = userRepository.findById(serviceEntry.mechanicId)
                    .orElseThrow { NotFoundException("Mechanic not found") }

            ServiceMapper.mapToServiceDto(serviceEntry, mechanic.workshopName.toString())
        }
    }
    fun getServicesByUserId(id: Long): List<ServiceEntryDto> {
        val services = serviceEntryRepository.findAllByUserId(id)
        return services.map { serviceEntry ->
            val mechanic = userRepository.findById(serviceEntry.mechanicId)
                    .orElseThrow { NotFoundException("Mechanic not found") }

            ServiceMapper.mapToServiceDto(serviceEntry, mechanic.workshopName.toString())
        }
    }
}

