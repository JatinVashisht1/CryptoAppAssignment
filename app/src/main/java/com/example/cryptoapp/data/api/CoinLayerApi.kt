package com.example.cryptoapp.data.api

import com.example.cryptoapp.BuildConfig
import com.example.cryptoapp.data.api.dto.livelist.CryptoRatesLiveResponseDto
import com.example.cryptoapp.data.api.dto.staticdata.CryptoCoinDetailsResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CoinLayerApi {
    @GET("live")
    /**
     * @param apiKey access key or API key of your account
     * @return returns [Response] of [CryptoRatesLiveResponseDto]
     * */
    suspend fun getLiveRates (
        @Query(QUERY_ACCESS_KEY) apiKey: String
    ): Response<CryptoRatesLiveResponseDto>

    /**
     * @param apiKey access key or API key of your account
     * @return returns [Response] of [CryptoCoinDetailsResponseDto]
     * */
    @GET("list")
    suspend fun getCoinDetails(
        @Query(QUERY_ACCESS_KEY) apiKey: String,
    ): Response<CryptoCoinDetailsResponseDto>

    companion object {
        const val QUERY_ACCESS_KEY = "access_key"
        const val API_KEY = BuildConfig.COIN_LAYER_API_KEY
    }
}