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
    var brand: String = "",
    var model: String = "",
    var year: String = "",
    var vin: String = "",
    var horsepower: String = "",
    var torque: String = "",
    var mileage: String = "",
    @OneToMany(mappedBy = "vehicle")
    val services: List<ServiceEntry>? = null
)

