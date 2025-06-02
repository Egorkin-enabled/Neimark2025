package ru.ivanovo_hack.preporate.common.userApi.data.api

import ru.ivanovo_hack.preporate.base.NetworkError
import ru.ivanovo_hack.preporate.base.Result
import ru.ivanovo_hack.preporate.common.userApi.data.api.models.*

class UserDataStorage(
    private val userApi: UserApi
){

    suspend fun signIn(sigInRequest: SignInRequest): Result<TokensResponse, NetworkError>{
        return userApi.signIn(sigInRequest)
    }
    suspend fun getUser(): Result<AuthUserResponse,NetworkError>{
        TODO()
    }

    suspend fun logOut(): Result<Unit,NetworkError>{
        return userApi.logOut()
    }
}