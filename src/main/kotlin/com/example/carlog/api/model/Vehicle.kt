package com.example.carlog.api.model

import jakarta.persistence.*
import lombok.*

@Entity
@Table(name = "vehicle")
class Vehicle (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: User = User(),
    val brand: String = "",
    val model: String = "",
    val year: String = "",
    val vin: String = "",
    val horsepower: String = "",
    val torque: String = "",
    @OneToMany(mappedBy = "vehicle")
    val services: List<ServiceEntry>? = null
)

