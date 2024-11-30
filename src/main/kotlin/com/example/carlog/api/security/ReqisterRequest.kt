package com.example.carlog.api.security

data class ReqisterRequest(val login: String, val password: String, val firstName: String, val lastName: String, val address: String, val email: String, val phoneNumber: String)
