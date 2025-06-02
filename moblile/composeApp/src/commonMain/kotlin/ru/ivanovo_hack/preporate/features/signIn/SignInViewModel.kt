package ru.ivanovo_hack.preporate.features.signIn

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.ivanovo_hack.preporate.base.Result
import ru.ivanovo_hack.preporate.base.BaseViewModel
import ru.ivanovo_hack.preporate.base.Error
import ru.ivanovo_hack.preporate.common.userApi.domain.useCases.GetUserUseCase
import ru.ivanovo_hack.preporate.features.signIn.models.SignInViewAction
import ru.ivanovo_hack.preporate.features.signIn.models.SignInViewEvent
import ru.ivanovo_hack.preporate.features.signIn.models.SignInViewUIState

class SignInViewModel(
    private val signInUseCase: SignInUseCase,
    private val getUserUseCase: GetUserUseCase,
): BaseViewModel<SignInViewUIState, SignInViewAction, SignInViewEvent>(SignInViewUIState(isLoading = true)){

    override fun obtainEvent(viewEvent: SignInViewEvent) {
        when(viewEvent){
            is SignInViewEvent.onEmailChanged -> changeEmail(viewEvent.value)
            is SignInViewEvent.onPsswordChanged -> changePassword(viewEvent.value)
            is SignInViewEvent.onLaunchScreen -> {
                viewState = viewState.copy(
                    isLoading = false
                )
            }
            is SignInViewEvent.signInClcik -> signIn()
        }
    }

    private fun signIn() {
        viewModelScope.launch {
            if (viewState.email == "email" && viewState.password == "password"){
                viewAction = SignInViewAction.goToHomeScreen("Oleg")
                return@launch
            }

            if(viewState.isLoading) return@launch

            val response = signInUseCase.invoke(viewState.email,viewState.password)

            val user = getUserUseCase()
            if (response is Result.Success && user is Result.Success){
                viewAction = SignInViewAction.goToHomeScreen(user.data.userFirstName)
                return@launch
            }

            viewState = SignInViewUIState()
            viewAction = SignInViewAction.showErrorPassword()
            return@launch
        }
    }

    private fun changeEmail(value: String){
        viewModelScope.launch {
            viewState = viewState.copy(
                email = value
            )
        }
    }

    private fun changePassword(value: String){
        viewModelScope.launch {
            viewState = viewState.copy(
                password = value
            )
        }
    }
}