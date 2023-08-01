package com.antisnusbolaget.slutasnusa2.ui.screens.onboardingscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.antisnusbolaget.slutasnusa2.ui.components.BottomNavOnBoarding
import com.antisnusbolaget.slutasnusa2.viewmodel.OnBoardingViewModel
import com.antisnusbolaget.slutasnusa2.viewmodel.`interface`.OnBoardingEvent
import com.antisnusbolaget.slutasnusa2.viewmodel.`interface`.OnBoardingLoadingState
import com.antisnusbolaget.slutasnusa2.viewmodel.`interface`.OnBoardingNavigationView
import com.antisnusbolaget.slutasnusa2.viewmodel.`interface`.OnBoardingState
import org.koin.androidx.compose.koinViewModel

@Composable
fun OnBoardingScreen(
    viewModel: OnBoardingViewModel = koinViewModel(),
) {
    val uiState = viewModel.uiState.collectAsState()

    when (uiState.value.error) {
        OnBoardingLoadingState.Error -> TODO()
        OnBoardingLoadingState.Loading -> CircularProgressIndicator()
        OnBoardingLoadingState.Success -> {
            OnBoardingContent(
                viewModel = viewModel,
                uiState = uiState,
            )
        }
    }
}

@Composable
private fun OnBoardingContent(
    viewModel: OnBoardingViewModel,
    uiState: State<OnBoardingState>,
) {
    fun uiEventSetCostPerUnit(cost: Int) = viewModel.handleEvents(OnBoardingEvent.SetCost(cost))
    fun uiEventSetUnits(units: Int) = viewModel.handleEvents(OnBoardingEvent.SetUnit(units))
    fun uiEventOpenCalendar() = viewModel.handleEvents(OnBoardingEvent.OpenCalendar)
    fun uiEventDismissCalendar() = viewModel.handleEvents(OnBoardingEvent.DismissCalendar)
    fun uiEventSetDate(date: Long) = viewModel.handleEvents(OnBoardingEvent.SetDate(date))
    fun uiEventNavigateBack() = viewModel.handleEvents(OnBoardingEvent.NavigateBack)

    Scaffold(
        bottomBar = {
            BottomNavOnBoarding(
                onClickNext = {
                    viewModel.handleEvents(OnBoardingEvent.NavigateToNextView)
                },
                onClickBack = {},
            )
        },
        content = { paddingValues ->
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(paddingValues),
            ) {
                when (uiState.value.currentView) {
                    OnBoardingNavigationView.CostView -> CostScreen(
                        onValueChangeFinished = { uiEventSetCostPerUnit(it) },
                        initialSliderValue = uiState.value.userData.costPerUnit.toFloat(),
                    )

                    OnBoardingNavigationView.UnitView -> UnitScreen(
                        onClickSetUnit = { uiEventSetUnits(it) },
                        amountOfUnitsLabel = uiState.value.userData.units.toString(),
                        onBackPress = { uiEventNavigateBack() },
                    )

                    is OnBoardingNavigationView.DateView -> {
                        DateScreen(
                            showCalendar = uiState.value.isCalenderVisible,
                            openCalendar = { uiEventOpenCalendar() },
                            dismissCalendar = { uiEventDismissCalendar() },
                            onDateSelected = { uiEventSetDate(it) },
                            onBackPressed = { uiEventNavigateBack() },
                        )
                    }
                }
            }
        },
    )
}
