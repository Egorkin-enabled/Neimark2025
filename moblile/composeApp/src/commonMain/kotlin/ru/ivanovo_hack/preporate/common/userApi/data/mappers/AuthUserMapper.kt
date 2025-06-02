package ru.ivanovo_hack.preporate.common.userApi.data.mappers

import ru.ivanovo_hack.preporate.common.userApi.data.api.models.AuthUserResponse
import ru.ivanovo_hack.preporate.common.userApi.domain.models.User
import ru.ivanovo_hack.preporate.common.userApi.domain.models.UserRole

fun AuthUserResponse.toUser()
    = User(
        userFirstName = firstName,
        userLastName = lastName,
        userName = username,
        id = id,
        userRole = getRoleFromString(roles)
    )

private fun getRoleFromString(role: String) : UserRole{
    return when(role){
        "teather" -> UserRole.Teather
        else -> UserRole.Student
    }
}