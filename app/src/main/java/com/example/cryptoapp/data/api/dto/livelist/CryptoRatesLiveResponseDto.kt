package com.example.cryptoapp.data.api.dto.livelist


/**
 * data class denoting DTO of live rates response of coins
 * */
data class CryptoRatesLiveResponseDto(
    val privacy: String,
    val rates: Map<String, Double>,
    val success: Boolean,
    val target: String,
    val terms: String,
    val timestamp: Int
)