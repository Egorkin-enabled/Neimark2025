
package ru.ivanovo_hack.preporate.common.userApi.data.api

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import ru.ivanovo_hack.preporate.base.Result
import ru.ivanovo_hack.preporate.base.NetworkError
import ru.ivanovo_hack.preporate.common.userApi.data.api.models.*

class UserApi(private val client: HttpClient){
    companion object{
        private const val USER_API_URL = "users/api/"
        private const val SIGN_IN_URL = USER_API_URL + "signin"
        private const val USER_URL = USER_API_URL + "accounts/me"
    }

    suspend fun signIn(sigInRequest: SignInRequest): Result<TokensResponse, NetworkError>{
        val response = client.post {
            url {
                path(SIGN_IN_URL)
            }
            setBody(sigInRequest)
        }

        return when(response.status){
            HttpStatusCode.Created -> {
                println(response)
                Result.Success(response.body())
            }
            HttpStatusCode.NotFound -> {
                Result.Error(NetworkError.NOT_FOUND)
            }
            else -> Result.Error(NetworkError.UNKNOWN)
        }
    }

    suspend fun logOut(): Result<Unit,NetworkError>{
        TODO()
    }
}
