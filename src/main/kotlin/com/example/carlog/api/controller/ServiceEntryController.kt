package com.example.carlog.api.controller

import com.example.carlog.api.dto.ServiceEntryDto
import com.example.carlog.api.service.ServiceEntryService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/services")
class ServiceEntryController ( private val serviceEntryService: ServiceEntryService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addServiceEntry(@RequestBody serviceEntryDto: ServiceEntryDto): ServiceEntryDto {
        return serviceEntryService.addService(serviceEntryDto)
    }
    @GetMapping("/vehicle/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getServiceEntriesByVehicleId(@PathVariable id: Long): List<ServiceEntryDto> {
        return serviceEntryService.getServicesByVehicleId(id)
    }
    @GetMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getServicesByUserId(@PathVariable id: Long): List<ServiceEntryDto> {
        return serviceEntryService.getServicesByUserId(id)
    }

}
