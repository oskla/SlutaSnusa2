package com.antisnusbolaget.slutasnusa2.ui.screens.onboardingscreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.antisnusbolaget.slutasnusa2.ui.screens.onboardingscreen.views.BottomNavOnBoarding
import com.antisnusbolaget.slutasnusa2.viewmodel.onboarding.OnBoardingEvent
import com.antisnusbolaget.slutasnusa2.viewmodel.onboarding.OnBoardingNavigationView
import com.antisnusbolaget.slutasnusa2.viewmodel.onboarding.OnBoardingViewModel
import org.koin.androidx.compose.koinViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun OnBoardingScreen(
    viewModel: OnBoardingViewModel = koinViewModel(),
) {
    val uiState = viewModel.uiState.collectAsState()

    fun uiEventSetCostPerUnit(cost: Int) = viewModel.handleEvents(OnBoardingEvent.SetCost(cost))
    fun uiEventSetUnits(units: Int) = viewModel.handleEvents(OnBoardingEvent.SetUnit(units))
    fun uiEventOpenCalendar() = viewModel.handleEvents(OnBoardingEvent.OpenCalendar)
    fun uiEventDismissCalendar() = viewModel.handleEvents(OnBoardingEvent.DismissCalendar)
    fun uiEventSetDate(date: Long) = viewModel.handleEvents(OnBoardingEvent.SetDate(date))
    fun uiEventNavigateBack() = viewModel.handleEvents(OnBoardingEvent.NavigateBack)
    fun uiEventNavigateNext() = viewModel.handleEvents(OnBoardingEvent.NavigateToNextView)

    Scaffold(
        bottomBar = {
            BottomNavOnBoarding(
                onClickNext = {
                    uiEventNavigateNext()
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
