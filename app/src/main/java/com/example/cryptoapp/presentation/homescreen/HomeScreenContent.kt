package com.example.cryptoapp.presentation.homescreen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.cryptoapp.presentation.homescreen.components.CryptoDetailItem
import com.example.cryptoapp.presentation.homescreen.uistate.HomeScreenState
import com.example.cryptoapp.presentation.utils.MyPaddingValues

/**
 * Composable function responsible for showing success state of [HomeScreenState]
 * @param modifier [Modifier] for the composable
 * @param homeScreenState [HomeScreenState.Success] denoting success state of screen
 * */
@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier,
    homeScreenState: HomeScreenState.Success,
) {
    LazyColumn(modifier = modifier) {
        items(homeScreenState.cryptoCoinDetailsModelList) { cryptoDetailModel ->
            CryptoDetailItem(
                cryptoCoinDetailsModel = cryptoDetailModel,
                cryptoCoinLiveState = homeScreenState.cryptoCoinLiveRateState,
                modifier = Modifier.padding(MyPaddingValues.SMALL,)
            )
            Divider()
        }
    }
}
