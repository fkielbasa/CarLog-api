package com.example.carlog.api.dto.mapper

import com.example.carlog.api.dto.ServiceEntryDto
import com.example.carlog.api.model.ServiceEntry
import com.example.carlog.api.model.User

class ServiceMapper {
    companion object {
        fun mapToServiceDto(serviceEntry: ServiceEntry, workshop: String): ServiceEntryDto {
            return ServiceEntryDto(
                    vehicleId = serviceEntry.vehicle.id ?: throw IllegalStateException("Vehicle id missing"),
                    mileage = serviceEntry.mileage.toString(),
                    date = serviceEntry.date.toString(),
                    description = serviceEntry.description,
                    price = serviceEntry.price.toString(),
                    mechanicId = serviceEntry.mechanicId,
                    vehicle = "${serviceEntry.vehicle.brand} ${serviceEntry.vehicle.model}",
                    workshop = workshop ?: "Unknown Workshop"
            )
        }
    }
}
