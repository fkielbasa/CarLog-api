package com.example.carlog.api.dto.mapper

import com.example.carlog.api.dto.ClientResponse
import com.example.carlog.api.dto.MechanicResponse
import com.example.carlog.api.model.User


class UserMapper {
    companion object {
        fun mapToClientResponse(user: User): ClientResponse {
            return ClientResponse(
                    firstName = user.firstName,
                    lastName = user.lastName,
                    address = user.address,
                    email = user.email,
                    phoneNumber = user.phoneNumber
            )
        }
        fun mapToMechanicResponse(user: User): MechanicResponse {
            return MechanicResponse(
                    firstName = user.firstName,
                    lastName = user.lastName,
                    address = user.address,
                    email = user.email,
                    phoneNumber = user.phoneNumber,
                    workshop = user.workshopName
            )
        }
    }
}
