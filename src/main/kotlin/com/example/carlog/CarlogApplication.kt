package com.example.carlog

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CarlogApplication

fun main(args: Array<String>) {
	runApplication<CarlogApplication>(*args)
}
