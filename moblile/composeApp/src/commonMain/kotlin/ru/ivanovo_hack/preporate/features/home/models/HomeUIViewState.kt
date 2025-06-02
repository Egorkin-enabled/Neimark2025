package ru.ivanovo_hack.preporate.features.home.models

import ru.ivanovo_hack.preporate.common.userApi.domain.models.User

data class HomeUIViewState(
    val isLoading: Boolean,
    val user: User? = null,
)