package com.example.cryptoapp.di

import com.example.cryptoapp.common.Constants
import com.example.cryptoapp.data.api.CoinLayerApi
import com.example.cryptoapp.data.repository.CryptoRepositoryImpl
import com.example.cryptoapp.domain.repository.ICryptoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    // provider function to tell hilt how to create object of `CoinLayerApi`
    fun providesCoinLayerApi(): CoinLayerApi = Retrofit
        .Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(Constants.COIN_LAYER_BASE_URL)
        .build()
        .create(CoinLayerApi::class.java)

    @Provides
    @Singleton
    fun providesICryptoRepository(
        coinLayerApi: CoinLayerApi,
        ioDispatcher: CoroutineDispatcher,
    ): ICryptoRepository = CryptoRepositoryImpl(
        coinLayerApi = coinLayerApi,
        ioDispatcher = ioDispatcher,
    )

    @Provides
    fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO
}