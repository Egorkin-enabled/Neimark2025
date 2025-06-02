package ru.ivanovo_hack.preporate.common.userApi.data.api.models

import kotlinx.serialization.Serializable

@Serializable
data class AuthUserResponse(
    val firstName: String,
    val lastName: String,
    val id: Int,
    val username: String,
    val roles: String,
)