package com.example.cryptoapp.presentation.homescreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.example.cryptoapp.R
import com.example.cryptoapp.domain.model.CryptoCoinDetailsModel
import com.example.cryptoapp.presentation.components.LoadingComposable
import com.example.cryptoapp.presentation.homescreen.uistate.CryptoCoinLiveState
import com.example.cryptoapp.presentation.utils.MyPaddingValues
import com.example.cryptoapp.presentation.utils.getDateTimeStringFromMillis

/**
 * composable for showing coin detail
 * @param modifier [Modifier] for Item
 * @param cryptoCoinDetailsModel [CryptoCoinDetailsModel] of the coin
 * @param cryptoCoinLiveState [CryptoCoinLiveState] of coins
 * */
@Composable
fun CryptoDetailItem(
    modifier: Modifier = Modifier,
    cryptoCoinDetailsModel: CryptoCoinDetailsModel,
    cryptoCoinLiveState: CryptoCoinLiveState,
    lastUpdated: Long,
) {
    Row(modifier = modifier) {
        SubcomposeAsyncImage(
            model = cryptoCoinDetailsModel.iconUrl,
            contentDescription = null,
            loading = {
                LoadingComposable(modifier = Modifier.size(MyPaddingValues.MEDIUM))
            },
            error = {
                Image(painter = painterResource(id = R.drawable.error_image_24), contentDescription = "error loading icon")
            },
            modifier = Modifier.size(64.dp)
        )
        Spacer(modifier = Modifier.width(MyPaddingValues.SMALL))

        Column(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(text = cryptoCoinDetailsModel.nameFull)
            Spacer(modifier = Modifier.height(MyPaddingValues.SMALL))
            LiveRateComponent(cryptoCoinLiveState = cryptoCoinLiveState, symbol = cryptoCoinDetailsModel.symbol)
            Spacer(modifier = Modifier.height(MyPaddingValues.SMALL))
            Text(text = "last updated: ${getDateTimeStringFromMillis(lastUpdated)}")
        }
    }
}