package com.example.cryptoapp.data.api

import com.example.cryptoapp.BuildConfig
import com.example.cryptoapp.data.api.dto.livelist.CryptoRatesLiveListResponseDto
import com.example.cryptoapp.data.api.dto.staticdata.CryptoCoinDetailsResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CoinLayerApi {
    @GET("live")
    /**
     * @param accessKey access key or API key of your account
     * @return returns [Response] of [CryptoRatesLiveListResponseDto]
     * */
    suspend fun getLiveRates (
        @Query(QUERY_ACCESS_KEY) accessKey: String
    ): Response<CryptoRatesLiveListResponseDto>

    /**
     * @param accessKey access key or API key of your account
     * @return returns [Response] of [CryptoCoinDetailsResponseDto]
     * */
    @GET("list")
    suspend fun getCoinDetails(
        @Query(QUERY_ACCESS_KEY) accessKey: String,
    ): Response<CryptoCoinDetailsResponseDto>

    companion object {
        const val QUERY_ACCESS_KEY = "access_key"
        const val API_KEY = BuildConfig.COIN_LAYER_API_KEY
    }
}