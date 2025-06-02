package ru.ivanovo_hack.preporate.common.userApi.domain.useCases

import ru.ivanovo_hack.preporate.base.Result
import ru.ivanovo_hack.preporate.base.NetworkError
import ru.ivanovo_hack.preporate.common.userApi.domain.UserRepository
import ru.ivanovo_hack.preporate.common.userApi.domain.models.User

class GetUserUseCase(
    private val userRepository: UserRepository
){

    suspend operator fun invoke(): Result<User, NetworkError>{
        return userRepository.getUser()
    }

}