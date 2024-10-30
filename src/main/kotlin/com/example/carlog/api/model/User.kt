package com.example.carlog.api.model

import jakarta.persistence.*

@Entity
@Table(name = "users")
class User(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,
        var username: String = "",
        var password: String = "",
        var firstName: String = "",
        var lastName: String = "",
){
        override fun toString(): String {
                return "User(id=$id, username='$username', password='$password')"
        }
}
