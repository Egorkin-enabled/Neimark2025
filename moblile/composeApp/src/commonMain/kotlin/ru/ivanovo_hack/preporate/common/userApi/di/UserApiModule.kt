package ru.ivanovo_hack.preporate.common.userApi.di

import org.koin.dsl.module
import ru.ivanovo_hack.preporate.common.userApi.data.UserRepositoryImpl
import ru.ivanovo_hack.preporate.common.userApi.data.api.UserApi
import ru.ivanovo_hack.preporate.common.userApi.data.api.UserDataStorage
import ru.ivanovo_hack.preporate.common.userApi.data.tokens.TokensDataStorage
import ru.ivanovo_hack.preporate.common.userApi.domain.UserRepository
import ru.ivanovo_hack.preporate.common.userApi.domain.useCases.GetApiTokensUseCase
import ru.ivanovo_hack.preporate.common.userApi.domain.useCases.GetUserUseCase

fun getUserApiModule()
    = module {

        single<UserApi> {
            UserApi(get())
        }

        single<UserDataStorage>{
            UserDataStorage(get())
        }

        single<TokensDataStorage> {
            TokensDataStorage()
        }

        single<UserRepository> {
            UserRepositoryImpl(get(),get())
        }

        factory<GetApiTokensUseCase> {
            GetApiTokensUseCase(get())
        }

        factory<GetUserUseCase> {
            GetUserUseCase(get())
        }

    }