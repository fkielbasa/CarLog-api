package com.example.carlog.api.exception

import org.springframework.http.HttpStatus
import org.springframework.validation.FieldError
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(VehicleNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleVehicleNotFoundException(ex: VehicleNotFoundException) {
    }
}
