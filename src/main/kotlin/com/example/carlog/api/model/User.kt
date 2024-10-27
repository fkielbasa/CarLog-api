package com.example.carlog.api.model

import jakarta.persistence.*

@Entity
@Table(name = "users")
class User(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,
        var username: String = "",
        var password: String = ""
)
