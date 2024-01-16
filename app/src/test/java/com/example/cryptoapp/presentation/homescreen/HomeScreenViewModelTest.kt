package com.example.cryptoapp.presentation.homescreen

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.example.cryptoapp.domain.model.CryptoCoinDetailsModel
import com.example.cryptoapp.domain.model.CryptoRatesLiveModel
import com.example.cryptoapp.domain.repository.ICryptoRepository
import com.example.cryptoapp.presentation.homescreen.uistate.HomeScreenState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class HomeScreenViewModelTest {
    @Mock
    lateinit var cryptoRepository: ICryptoRepository
    private val testDispatcher = StandardTestDispatcher()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup () {
        Dispatchers.setMain(testDispatcher)
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun successStateTest() = runTest {
        Mockito.doReturn(flowOf(Result.success(listOf<CryptoCoinDetailsModel>()))).`when`(cryptoRepository).getCoinDetails()
        Mockito.doReturn(flowOf(Result.success(CryptoRatesLiveModel()))).`when`(cryptoRepository).getCoinLiveDetails()

        val homeScreenViewModel = HomeScreenViewModel(cryptoRepository,)

        homeScreenViewModel.homeScreenState.test {
            assertEquals(HomeScreenState.Success(), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test(expected = Exception::class)
    fun failedStateTest() = runTest {
        Mockito.doReturn(flowOf(Result.failure<CryptoCoinDetailsModel>(Exception("something went wrong")))).`when`(cryptoRepository).getCoinDetails()
        Mockito.doReturn(flowOf(Result.failure<CryptoRatesLiveModel>(Exception("unable to fetch exchange rates")))).`when`(cryptoRepository).getCoinLiveDetails()

        val homeScreenViewModel = HomeScreenViewModel(cryptoRepository,)

        homeScreenViewModel.homeScreenState.test {
            assertEquals(HomeScreenState.Success(), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }
}