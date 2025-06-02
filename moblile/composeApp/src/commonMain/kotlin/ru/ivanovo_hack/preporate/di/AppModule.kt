package ru.ivanovo_hack.preporate.di

import io.ktor.client.HttpClient
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module
import ru.ivanovo_hack.preporate.common.ktorClient.getHttpClient
import ru.ivanovo_hack.preporate.features.signIn.getSignInModule

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin{
        appDeclaration()
        modules(getAppModule())
    }

fun getAppModule() = listOf(
        getHttpClientAuth(),
    ) + getFeatureModules()

fun getFeatureModules() = listOf(
        getSignInModule(),
    )

fun getHttpClientAuth() = module {
        single<HttpClient>{
            getHttpClient(get())
        }
    }