package ru.ivanovo_hack.preporate.common.userApi.data.api.models

import kotlinx.serialization.Serializable

@Serializable
data class TokensResponse(
    val accessToken: String,
)