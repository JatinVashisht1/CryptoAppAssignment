package com.example.cryptoapp.presentation.homescreen.uistate

import com.example.cryptoapp.domain.model.CryptoCoinDetailsModel
import com.example.cryptoapp.domain.model.CryptoRatesLiveModel

sealed class HomeScreenState {
    data object Loading: HomeScreenState()
    data class Failure(val errorMessage: String): HomeScreenState()
    data class Success(
        val cryptoCoinDetailsModelList: List<CryptoCoinDetailsModel> = emptyList(),
        val cryptoCoinLiveRateState: CryptoCoinLiveState = CryptoCoinLiveState.Loading,
    ): HomeScreenState()
}

sealed class CryptoCoinLiveState {
    data object Loading: CryptoCoinLiveState()
    data class Failure(val errorMessage: String): CryptoCoinLiveState()
    data class Success(
        val cryptoCoinLiveModel: CryptoRatesLiveModel = CryptoRatesLiveModel(),
    ): CryptoCoinLiveState()
}