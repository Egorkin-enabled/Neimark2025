package ru.ivanovo_hack.preporate.common.userApi.domain.models

data class User(
    val userFirstName: String,
    val userLastName: String,
    val id: Int,
    val userName: String,
    val userRole: UserRole
)

enum class UserRole{
    Teather,
    Student,
}