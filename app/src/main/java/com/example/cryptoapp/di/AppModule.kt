package com.example.cryptoapp.di

import com.example.cryptoapp.common.Constants
import com.example.cryptoapp.data.api.CoinLayerApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
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
}