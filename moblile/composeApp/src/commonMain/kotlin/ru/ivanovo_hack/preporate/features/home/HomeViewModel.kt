package ru.ivanovo_hack.preporate.features.home

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.ivanovo_hack.preporate.base.BaseViewModel
import ru.ivanovo_hack.preporate.base.Result
import ru.ivanovo_hack.preporate.common.userApi.domain.useCases.GetUserUseCase
import ru.ivanovo_hack.preporate.features.home.models.HomeAction
import ru.ivanovo_hack.preporate.features.home.models.HomeEvent
import ru.ivanovo_hack.preporate.features.home.models.HomeUIViewState

class HomeViewModel (
    private val getUserUseCase: GetUserUseCase,
) : BaseViewModel<HomeUIViewState, HomeAction, HomeEvent>(HomeUIViewState(isLoading = true)){

    override fun obtainEvent(viewEvent: HomeEvent) {

    }

    init {
        viewModelScope.launch {
            val user = getUserUseCase.invoke()

            if (user is Result.Success){
                viewState = HomeUIViewState(
                    isLoading = false,
                    user = user.data,
                )
            }
        }
    }
}