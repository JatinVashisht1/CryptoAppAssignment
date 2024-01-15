package com.example.cryptoapp.data.api.dto.livelist


data class CryptoRatesLiveListResponseDto(
    val privacy: String,
    val rates: Map<String, Double>,
    val success: Boolean,
    val target: String,
    val terms: String,
    val timestamp: Int
)