package ru.ivanovo_hack.preporate.common.userApi.domain.models

data class AuthUser(
    val user: User,
    val tokens: ApiTokens,
)

data class ApiTokens(
    val accessToken: String,
)