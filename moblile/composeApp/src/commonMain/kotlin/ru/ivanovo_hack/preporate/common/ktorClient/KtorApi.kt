package ru.ivanovo_hack.preporate.common.ktorClient

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import ru.ivanovo_hack.preporate.common.userApi.data.tokens.TokensDataStorage
import ru.ivanovo_hack.preporate.common.userApi.domain.models.ApiTokens

const val BASE_URL = "http://85.192.61.121:8086/"

fun getHttpClient(
    tokensDataStorage: TokensDataStorage,
) = HttpClient{
    install(Logging){
        level = LogLevel.ALL
    }
    install(DefaultRequest){
        url(BASE_URL)
        contentType(ContentType.Application.Json)
    }
    install(ContentNegotiation){
        json(Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
        })
    }
    install(Auth){
        bearer {
            loadTokens {
                val tokens = ApiTokens(
                    accessToken = tokensDataStorage.getAccessToken(),
                )
                BearerTokens(tokens.accessToken, null)
            }
        }
    }
}