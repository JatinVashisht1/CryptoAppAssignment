package com.example.cryptoapp.presentation.homescreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cryptoapp.presentation.components.ErrorComposable
import com.example.cryptoapp.presentation.components.LoadingComposable
import com.example.cryptoapp.presentation.homescreen.uistate.HomeScreenState
import com.example.cryptoapp.presentation.utils.MyPaddingValues

/**
 * Composable that is responsible for showing user the HomeScreen
 * @param homeScreenViewModel [HomeScreenViewModel] instance for the HomeScreen
 * */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreenParent(
    homeScreenViewModel: HomeScreenViewModel = hiltViewModel(),
) {
    val homeScreenState by homeScreenViewModel.homeScreenState.collectAsState()
    var isRefreshing by remember { mutableStateOf(false) }
    var lastUpdated by rememberSaveable { mutableLongStateOf(System.currentTimeMillis()) }

    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = {
            isRefreshing = true
            homeScreenViewModel.onRetryTriggered()
            lastUpdated = System.currentTimeMillis()
            isRefreshing = false
        },
    )

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (val currentScreenState = homeScreenState) {
            is HomeScreenState.Failure -> {
                ErrorComposable(
                    modifier = Modifier
                        .fillMaxSize()
                        .pullRefresh(pullRefreshState),
                    error = currentScreenState.errorMessage,
                    shouldShowRetryButton = true,
                    onRetryButtonClick = homeScreenViewModel::onRetryTriggered,
                )

            }

            HomeScreenState.Loading -> {
                LoadingComposable(modifier = Modifier.size(MyPaddingValues.LARGE))
            }

            is HomeScreenState.Success -> {
                HomeScreenContent(
                    homeScreenState = currentScreenState,
                    modifier = Modifier
                        .fillMaxSize()
                        .pullRefresh(pullRefreshState),
                    lastUpdated = lastUpdated,
                )
            }

        }

        PullRefreshIndicator(
            refreshing = isRefreshing,
            state = pullRefreshState,
            modifier = Modifier.align(
                Alignment.TopCenter
            ),
        )
    }
}

private const val TAG = "homescreentag"