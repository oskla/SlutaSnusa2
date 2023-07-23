package com.antisnusbolaget.slutasnusa2.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.antisnusbolaget.slutasnusa2.ui.components.BottomNavOnBoarding
import com.antisnusbolaget.slutasnusa2.viewmodel.OnBoardingViewModel
import com.antisnusbolaget.slutasnusa2.viewmodel.`interface`.OnBoardingEvent
import com.antisnusbolaget.slutasnusa2.viewmodel.`interface`.OnBoardingScreenState
import org.koin.androidx.compose.koinViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun OnBoardingScreen(
    viewModel: OnBoardingViewModel = koinViewModel(),
) {
    val uiState = viewModel.uiState.collectAsState()

    Scaffold(
        bottomBar = {
            BottomNavOnBoarding(
                onClickNext = { viewModel.handleEvents(OnBoardingEvent.NavigateToNextState) },
                onClickBack = {},
            )
        },
        content = {
            Box() {
                when (uiState.value) {
                    OnBoardingScreenState.CostScreenState -> CostScreen()
                    OnBoardingScreenState.UnitScreenState -> UnitScreen()
                    OnBoardingScreenState.DateScreenState -> DateScreen()
                }
            }
        },
    )
}
