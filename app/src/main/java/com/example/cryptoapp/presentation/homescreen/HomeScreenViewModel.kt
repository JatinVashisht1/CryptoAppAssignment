package com.example.cryptoapp.presentation.homescreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptoapp.domain.repository.ICryptoRepository
import com.example.cryptoapp.presentation.homescreen.uistate.CryptoCoinLiveState
import com.example.cryptoapp.presentation.homescreen.uistate.HomeScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * ViewModel for HomeScreen
 * @param cryptoRepository instance of [ICryptoRepository]
 * */
@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val cryptoRepository: ICryptoRepository,
) : ViewModel() {
    private var pollingJob: Job? = null
    private val _homeScreenState = MutableStateFlow<HomeScreenState>(HomeScreenState.Loading)
    val homeScreenState = _homeScreenState.asStateFlow()

    init {
        viewModelScope.launch {
//            getStaticData()
            launch {
                getCoinDetail()
            }.join()

            launch {
                startServerPolling()
            }
        }
    }

    /**
     * function responsible for getting live exchange rates of coins
     * */
    private suspend fun getLiveData() {
        val currentScreenState = _homeScreenState.value
        if (currentScreenState !is HomeScreenState.Success) return
//        Logger.debug(TAG, "inside get live data after checking state")
        _homeScreenState.value = currentScreenState.copy(
            cryptoCoinLiveRateState = CryptoCoinLiveState.Loading,
        )
        cryptoRepository.getCoinLiveDetails()
            .collectLatest { result ->
                result.onSuccess { cryptoRatesLiveModel ->
                    _homeScreenState.value = currentScreenState.copy(
                        cryptoCoinLiveRateState = CryptoCoinLiveState.Success(cryptoRatesLiveModel)
                    )
                }

                result.onFailure {
                    pollingJob?.cancel()
                    _homeScreenState.value = currentScreenState.copy(
                        cryptoCoinLiveRateState = CryptoCoinLiveState.Failure(errorMessage = it.message?: "not available at the moment")
                    )
                }
            }

    }

    /**
     * function for starting server polling that will update the exchange rates after regular interval
     * */
    private fun startServerPolling() {
        pollingJob?.cancel()
        pollingJob = viewModelScope.launch {
            while (isActive) {
                getLiveData()
                delay(TimeUnit.MINUTES.toMillis(THREE_MINS))
            }
        }
    }

    /**
     * function responsible for getting static data, i.e, coin details
     * */
    private suspend fun getCoinDetail() {
        cryptoRepository.getCoinDetails()
            .collectLatest { result ->
//                    Logger.debug(TAG, "result is $result")
                result.onSuccess { cryptoCoinDetailModelList ->
                    _homeScreenState.value = HomeScreenState.Success(
                        cryptoCoinDetailsModelList = cryptoCoinDetailModelList,
                    )
                }

                result.onFailure {
                    _homeScreenState.value =
                        HomeScreenState.Failure(
                            errorMessage = it.message ?: "Something went wrong"
                        )
                }
            }
    }

    /**
     * function handling logic of retry in viewmodel
     * */
    fun onRetryTriggered() {
        viewModelScope.launch {
            _homeScreenState.value = HomeScreenState.Loading
            launch {
                getCoinDetail()
            }.join()

            getLiveData()
        }
    }

    override fun onCleared() {
        super.onCleared()
        pollingJob?.cancel()
    }


    companion object {
        const val TAG = "homescreenviewmodel"
        const val THREE_MINS = 3L
    }
}
