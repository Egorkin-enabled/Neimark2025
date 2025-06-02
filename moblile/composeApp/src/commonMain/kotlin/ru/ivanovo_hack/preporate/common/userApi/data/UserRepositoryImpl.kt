package ru.ivanovo_hack.preporate.common.userApi.data

import ru.ivanovo_hack.preporate.base.Result
import ru.ivanovo_hack.preporate.base.NetworkError
import ru.ivanovo_hack.preporate.base.map
import ru.ivanovo_hack.preporate.common.userApi.data.api.UserDataStorage
import ru.ivanovo_hack.preporate.common.userApi.data.api.models.SignInRequest
import ru.ivanovo_hack.preporate.common.userApi.data.mappers.toUser
import ru.ivanovo_hack.preporate.common.userApi.data.tokens.TokensDataStorage
import ru.ivanovo_hack.preporate.common.userApi.domain.UserRepository
import ru.ivanovo_hack.preporate.common.userApi.domain.models.User

class UserRepositoryImpl(
    private val userDataStorage: UserDataStorage,
    private val tokensDataStorage: TokensDataStorage,
) : UserRepository {

    override suspend fun signIn(userName: String, password: String): Result<Unit, NetworkError> {
        val tokens = userDataStorage.signIn(
            SignInRequest(
                userName,password
            )
        )

        if (tokens is Result.Success){
            tokensDataStorage.run {
                saveAccessToken(tokens.data.accessToken)
            }
        }

        return tokens.map { Unit}
    }

    override suspend fun getUser(): Result<User,NetworkError> {
        return userDataStorage.getUser().map {
            it.toUser()
        }
    }

    override suspend fun logOut(): Result<Unit,NetworkError> {
        TODO("Not yet implemented")
    }
}