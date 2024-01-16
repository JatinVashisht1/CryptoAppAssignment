package com.example.cryptoapp.presentation.homescreen.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import com.example.cryptoapp.common.utils.roundTo
import com.example.cryptoapp.presentation.homescreen.uistate.CryptoCoinLiveState

/**
 * composable function that shows live exchange rates of the coin
 * @param cryptoCoinLiveState [CryptoCoinLiveState] of the coin
 * @param symbol symbol of coin in form of String
 * */
@Composable
fun LiveRateComponent(
    cryptoCoinLiveState: CryptoCoinLiveState,
    symbol: String,
) {
    Row {
        Text(text = "exchange rate: ")
        when (cryptoCoinLiveState) {
            is CryptoCoinLiveState.Failure -> {
                Text(
                    text = cryptoCoinLiveState.errorMessage,
                    color = MaterialTheme.colorScheme.error,
                )
            }

            CryptoCoinLiveState.Loading -> {
                Text(text = "updating")
            }

            is CryptoCoinLiveState.Success -> {

                val exchangeRate = remember {
                    cryptoCoinLiveState.cryptoCoinLiveModel.cryptoCoinsExchangeRate[symbol]
                }

                Text(
                    text = "USD ${
                        exchangeRate?.roundTo(roundToPlaces = 6)?.toString() ?: " not available"
                    }",
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}
