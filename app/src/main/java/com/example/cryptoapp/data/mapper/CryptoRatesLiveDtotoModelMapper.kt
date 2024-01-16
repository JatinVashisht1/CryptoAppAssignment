package com.example.cryptoapp.data.mapper

import com.example.cryptoapp.data.api.dto.livelist.CryptoRatesLiveResponseDto
import com.example.cryptoapp.domain.model.CryptoRatesLiveModel

/**
 * extension function that maps [CryptoRatesLiveResponseDto] to [CryptoRatesLiveModel]
 * @return [CryptoRatesLiveModel]
 * */
fun CryptoRatesLiveResponseDto.toCryptoRatesLiveModel(): CryptoRatesLiveModel {
    return CryptoRatesLiveModel(cryptoCoinsExchangeRate = rates)
}