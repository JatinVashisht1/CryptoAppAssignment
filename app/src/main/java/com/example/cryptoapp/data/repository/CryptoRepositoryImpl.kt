package com.example.cryptoapp.data.repository

import com.example.cryptoapp.BuildConfig
import com.example.cryptoapp.common.utils.Logger
import com.example.cryptoapp.data.api.CoinLayerApi
import com.example.cryptoapp.data.mapper.toCryptoCoinDetailsModel
import com.example.cryptoapp.data.mapper.toCryptoRatesLiveModel
import com.example.cryptoapp.domain.model.CryptoCoinDetailsModel
import com.example.cryptoapp.domain.model.CryptoRatesLiveModel
import com.example.cryptoapp.domain.repository.ICryptoRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * class extending [ICryptoRepository] and responsible for interacting with external API
 * */
class CryptoRepositoryImpl @Inject constructor(
    private val coinLayerApi: CoinLayerApi,
    private val ioDispatcher: CoroutineDispatcher,
) : ICryptoRepository {
    override suspend fun getCoinDetails(): Flow<Result<List<CryptoCoinDetailsModel>>> = flow{
            try {
                val coinDetailsResponse = coinLayerApi.getCoinDetails(apiKey = BuildConfig.COIN_LAYER_API_KEY)
                val isResponseSuccessful = coinDetailsResponse.isSuccessful
                if (!isResponseSuccessful) {
                    throw Exception("unable to fetch data at the moment")
                }

                val coinDetailDto = coinDetailsResponse.body()
                val coinDetailsModelList = coinDetailDto?.toCryptoCoinDetailsModel() ?: emptyList()
                emit(Result.success(coinDetailsModelList))
            } catch (e: retrofit2.HttpException) {
                Logger.debug(TAG, "http exception while getting coin details: $e")
                emit(Result.failure(Throwable("unable to get success response from server, please try again later")))
            } catch (e: Exception) {
                Logger.debug(TAG, "http exception while getting coin details: $e")
                emit(Result.failure(Throwable("something went wrong, please try again later")))
            }
        }
        .flowOn(ioDispatcher)

    override suspend fun getCoinLiveDetails(): Flow<Result<CryptoRatesLiveModel>> =
        flow {
            try {
                val coinLiveResponse = coinLayerApi.getLiveRates(apiKey = BuildConfig.COIN_LAYER_API_KEY)
                val isResponseSuccessful = coinLiveResponse.isSuccessful

                if (!isResponseSuccessful) throw Exception("unable to fetch data at the moment")

                val coinExchangeRateDto = coinLiveResponse.body()
                val coinDetailsModel =
                    coinExchangeRateDto?.toCryptoRatesLiveModel() ?: CryptoRatesLiveModel()

                emit(Result.success(coinDetailsModel))
            } catch (e: retrofit2.HttpException) {
                Logger.debug(TAG, "http exception while getting coin details: $e")
                emit(Result.failure(Throwable("unable to get success response from server, please try again later")))
            } catch (e: Exception) {
                Logger.debug(TAG, "http exception while getting coin details: $e")
                emit(Result.failure(Throwable("something went wrong, please try again later")))
            }
        }.flowOn(ioDispatcher)

    companion object {
        const val TAG = "cryptorepositoryimpl"
        const val POLLING_MINS = 1L

    }
}