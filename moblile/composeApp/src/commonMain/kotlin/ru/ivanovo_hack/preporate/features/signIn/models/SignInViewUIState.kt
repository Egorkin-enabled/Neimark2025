package ru.ivanovo_hack.preporate.features.signIn.models

data class SignInViewUIState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false
)