package com.example.cryptoapp.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.cryptoapp.presentation.utils.MyPaddingValues


/**
 * composable function for showing any error message to user
 * @param modifier Jetpack Compose [Modifier] for error composable
 * @param error error message to be shown
 * @param shouldShowRetryButton boolean denoting whether retry button should be visible or not
 * @param onRetryButtonClick callback function for implement retry logic
 * */
@Composable
fun ErrorComposable(
    modifier: Modifier = Modifier,
    error: String = "",
    shouldShowRetryButton: Boolean = false,
    onRetryButtonClick: () -> Unit = {},
) {
    LazyColumn(modifier = modifier, verticalArrangement = Arrangement.Center) {
        item {
            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally,) {
                Text(text = error, color = MaterialTheme.colorScheme.error)

                if (shouldShowRetryButton) {
                    Spacer(modifier = Modifier.padding(MyPaddingValues.MEDIUM))
                    Button(onClick = onRetryButtonClick) {
                        Text(text = "Retry")
                    }
                }
            }
        }
    }
}