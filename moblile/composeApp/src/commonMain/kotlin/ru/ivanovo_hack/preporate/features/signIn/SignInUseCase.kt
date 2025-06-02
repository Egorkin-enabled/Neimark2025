package ru.ivanovo_hack.preporate.features.signIn

import ru.ivanovo_hack.preporate.common.userApi.domain.UserRepository
import ru.ivanovo_hack.preporate.base.Result
import ru.ivanovo_hack.preporate.base.NetworkError

class SignInUseCase(
    private val userRepository: UserRepository
){

    suspend operator fun invoke(userName: String, password: String): Result<Unit,NetworkError>{
        return userRepository.signIn(userName, password)
    }

}