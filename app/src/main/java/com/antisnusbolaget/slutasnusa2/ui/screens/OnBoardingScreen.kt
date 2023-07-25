package com.antisnusbolaget.slutasnusa2.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.antisnusbolaget.slutasnusa2.ui.components.BottomNavOnBoarding
import com.antisnusbolaget.slutasnusa2.viewmodel.OnBoardingViewModel
import com.antisnusbolaget.slutasnusa2.viewmodel.`interface`.OnBoardingEvent
import com.antisnusbolaget.slutasnusa2.viewmodel.`interface`.OnBoardingNavigationView
import org.koin.androidx.compose.koinViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun OnBoardingScreen(
    viewModel: OnBoardingViewModel = koinViewModel(),
) {
    val currentViewState = viewModel.currentView.collectAsState()
    val userData = viewModel.userData.collectAsState()

    Scaffold(
        bottomBar = {
            BottomNavOnBoarding(
                onClickNext = {
                    viewModel.handleEvents(OnBoardingEvent.NavigateToNextView)
                },
                onClickBack = {},
            )
        },
        content = {
            Box() {
                when (currentViewState.value) {
                    OnBoardingNavigationView.CostView -> CostScreen()
                    OnBoardingNavigationView.UnitView -> UnitScreen(
                        onEvent = { event ->
                            viewModel.handleEvents(event)
                        },
                        amountOfUnitsLabel = userData.value.units.toString(),
                    )
                    OnBoardingNavigationView.DateView -> DateScreen()
                }
            }
        },
    )
}
