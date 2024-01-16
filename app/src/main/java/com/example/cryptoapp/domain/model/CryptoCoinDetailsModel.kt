package com.example.cryptoapp.domain.model

/**
 * data class denoting model class for coin details required
 * */
data class CryptoCoinDetailsModel(
    val nameFull: String = "",
    val iconUrl: String = "",
    val symbol: String = "",
)