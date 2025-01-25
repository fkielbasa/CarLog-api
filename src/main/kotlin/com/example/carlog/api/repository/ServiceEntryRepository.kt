package com.example.carlog.api.repository

import com.example.carlog.api.model.ServiceEntry
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.*

interface ServiceEntryRepository: JpaRepository<ServiceEntry, Long> {
    fun getServiceEntriesByVehicleId(vehicleId: Long): Optional<List<ServiceEntry>>
    @Query("SELECT s FROM ServiceEntry s WHERE s.vehicle.user.id = :userId")
    fun findAllByUserId(@Param("userId") userId: Long): List<ServiceEntry>
}
