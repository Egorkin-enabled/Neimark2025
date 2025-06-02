package ru.ivanovo_hack.preporate.common.userApi.domain


import ru.ivanovo_hack.preporate.base.Result
import ru.ivanovo_hack.preporate.base.NetworkError
import ru.ivanovo_hack.preporate.common.userApi.domain.models.User

interface UserRepository {

    suspend fun signIn(
        userName: String,
        password: String,
    ): Result<Unit, NetworkError>

    suspend fun getUser(): Result<User,NetworkError>
    suspend fun logOut(): Result<Unit,NetworkError>

}