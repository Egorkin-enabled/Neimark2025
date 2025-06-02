package ru.ivanovo_hack.preporate.common.userApi.data.tokens

import com.russhwolf.settings.Settings

class TokensDataStorage(){

    val settings = Settings()

    fun saveAccessToken(accessToken: String){
        settings.putString(ACCESS_TOKEN_KEY, accessToken)
    }

    fun getAccessToken(): String{
        return settings.getString(ACCESS_TOKEN_KEY, "")
    }

    companion object{
        private const val ACCESS_TOKEN_KEY = "ACCESS_TOKEN_KEY"
    }
}