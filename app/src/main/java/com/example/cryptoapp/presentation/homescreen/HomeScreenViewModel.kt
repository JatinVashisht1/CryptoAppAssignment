package com.example.cryptoapp.presentation.homescreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptoapp.BuildConfig
import com.example.cryptoapp.common.utils.Logger
import com.example.cryptoapp.data.api.CoinLayerApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor (
    private val coinLayerApi: CoinLayerApi,
): ViewModel() {
    init {
        viewModelScope.launch {
            val response = coinLayerApi.getCoinDetails(BuildConfig.COIN_LAYER_API_KEY)

            Logger.debug(TAG, "response code is ${response.code()} response data is ${response.body()}")
        }
    }

    companion object {
        const val TAG = "homescreenviewmodel"
    }
}