package ru.ivanovo_hack.preporate.features.signIn.models

sealed interface SignInViewAction{
    class goToHomeScreen(val userFirstName: String) : SignInViewAction
    class showErrorEmail : SignInViewAction
    class showErrorPassword : SignInViewAction

}