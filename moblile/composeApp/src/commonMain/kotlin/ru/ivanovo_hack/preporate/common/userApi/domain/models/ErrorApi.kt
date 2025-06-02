package ru.ivanovo_hack.preporate.common.userApi.domain.models

sealed class ErrorApi : Exception(){
    class Unauthorized : ErrorApi()
    class ErrorPassword : ErrorApi()

    class ErrorLogin : ErrorApi()
}