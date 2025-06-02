package ru.ivanovo_hack.preporate.features.signIn

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

fun getSignInModule() = module {
        factory<SignInUseCase> {
            SignInUseCase(get())
        }

        viewModel { SignInViewModel(get(),get())}
    }