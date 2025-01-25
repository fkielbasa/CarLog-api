package com.example.carlog.api.repository

import com.example.carlog.api.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.Optional

interface UserRepository: JpaRepository<User, Long> {
    fun findUserByLogin(user: String): User?
    fun existsByLoginOrEmail(login: String?, email: String?): Boolean
    fun findUserByVehicles_Id(vehicleId: Long): Optional<User>
    @Query("SELECT u FROM User u JOIN u.vehicles v JOIN v.services s WHERE s.id = :serviceId")
    fun findUserByServiceId(@Param("serviceId") serviceId: Long): Optional<User>
}
