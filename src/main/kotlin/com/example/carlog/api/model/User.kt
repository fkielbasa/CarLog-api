package com.example.carlog.api.model

import com.example.carlog.api.dto.Role
import jakarta.persistence.*

@Entity
@Table(name = "users")
class User(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        val login: String = "",
        var password: String = "",
        val firstName: String = "",
        val lastName: String = "",
        val address: String = "",
        val email: String = "",
        val phoneNumber: String = "",
        @Column(name = "workshop_name")
        val workshopName: String? = null,
        @Enumerated(EnumType.STRING)
        val role: Role = Role.CLIENT,
        @OneToMany(mappedBy = "user")
        val vehicles: List<Vehicle>? = mutableListOf()
) {
}
