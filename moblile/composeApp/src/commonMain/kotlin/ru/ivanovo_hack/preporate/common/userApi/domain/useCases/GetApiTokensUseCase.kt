package ru.ivanovo_hack.preporate.common.userApi.domain.useCases

import ru.ivanovo_hack.preporate.common.userApi.data.tokens.TokensDataStorage
import ru.ivanovo_hack.preporate.common.userApi.domain.models.ApiTokens

class GetApiTokensUseCase(
    private val tokensDataStorage: TokensDataStorage
){

    operator fun invoke(): ApiTokens {
        return  ApiTokens(
            accessToken = tokensDataStorage.getAccessToken(),
        )
    }

}