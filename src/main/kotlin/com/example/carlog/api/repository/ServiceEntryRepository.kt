package com.example.carlog.api.repository

import com.example.carlog.api.model.ServiceEntry
import org.springframework.data.jpa.repository.JpaRepository

interface ServiceEntryRepository: JpaRepository<ServiceEntry, Long> {
}
