package ru.ivanovo_hack.preporate.features.signIn.models

sealed interface SignInViewEvent{
    data class onEmailChanged(val value: String): SignInViewEvent
    data class onPsswordChanged(val value: String): SignInViewEvent

    class onLaunchScreen: SignInViewEvent
    class signInClcik: SignInViewEvent
}