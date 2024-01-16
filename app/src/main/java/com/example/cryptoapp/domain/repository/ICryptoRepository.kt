package com.example.cryptoapp.domain.repository

import com.example.cryptoapp.domain.model.CryptoCoinDetailsModel
import com.example.cryptoapp.domain.model.CryptoRatesLiveModel
import kotlinx.coroutines.flow.Flow

/**
 * interface for getting coin details and live details
 * */
interface ICryptoRepository {
    /**
     * function that gets coin details from external data source
     * @return [Flow] of list containing [CryptoCoinDetailsModel] encapsulated in kotlin [Result] block
     * */
    suspend fun getCoinDetails(): Flow<Result<List<CryptoCoinDetailsModel>>>

    /**
     * function that gets live exchange rates of coins from external data source
     * @return [Flow] of list containing [CryptoCoinDetailsModel] encapsulated in kotlin [Result] block
     * */
    suspend fun getCoinLiveDetails(): Flow<Result<CryptoRatesLiveModel>>
}