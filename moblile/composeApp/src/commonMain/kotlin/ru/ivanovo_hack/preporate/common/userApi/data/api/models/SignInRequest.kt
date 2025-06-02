package ru.ivanovo_hack.preporate.common.userApi.data.api.models

import kotlinx.serialization.Serializable

@Serializable
data class SignInRequest(
    val password: String,
    val username: String,
)
