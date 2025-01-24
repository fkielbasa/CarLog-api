package com.example.carlog.api.model

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDate
import java.util.Date

@Entity
@Table(name = "services")
class ServiceEntry(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "vehicle_id", nullable = false)
        val vehicle: Vehicle = Vehicle(),

        @Column(nullable = false)
        val mechanicId: Long = 0,

        @Column(nullable = false, columnDefinition = "DATE")
        val date: LocalDate = LocalDate.now(),

        @Column(nullable = false)
        val price: BigDecimal = BigDecimal(0),

        @Column(nullable = false)
        val mileage: Long = 0,

        @Column(nullable = false, length = 500)
        val description: String = ""
)
