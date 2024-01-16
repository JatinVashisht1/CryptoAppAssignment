package com.example.cryptoapp.domain.model

typealias CryptoSymbol = String
typealias ExchangeValue = Double

/**
 * data class denoting live exchange rates of each coin
 * */
data class CryptoRatesLiveModel (
    val cryptoCoinsExchangeRate: Map<CryptoSymbol, ExchangeValue> = emptyMap(),
)