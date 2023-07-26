package com.antisnusbolaget.slutasnusa2.ui.screens.onboardingscreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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

    fun uiEventSetCostPerUnit(cost: Int) = viewModel.handleEvents(OnBoardingEvent.SetCost(cost))
    fun uiEventSetUnits(units: Int) = viewModel.handleEvents(OnBoardingEvent.SetUnit(units))
    fun uiEventOpenCalendar() = viewModel.handleEvents(OnBoardingEvent.OpenCalendar)
    fun uiEventDismissCalendar() = viewModel.handleEvents(OnBoardingEvent.DismissCalendar)
    fun uiEventSetDate(date: Long) = viewModel.handleEvents(OnBoardingEvent.SetDate(date))

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
                when (currentViewState.value) {
                    OnBoardingNavigationView.CostView -> CostScreen(
                        onValueChangeFinished = { uiEventSetCostPerUnit(it) },
                    )

                    OnBoardingNavigationView.UnitView -> UnitScreen(
                        onClickSetUnit = { uiEventSetUnits(it) },
                        amountOfUnitsLabel = userData.value.units.toString(),
                    )

                    is OnBoardingNavigationView.DateView -> {
                        val calendarVisibility =
                            (currentViewState.value as OnBoardingNavigationView.DateView).showCalendar

                        DateScreen(
                            showCalendar = calendarVisibility,
                            openCalendar = { uiEventOpenCalendar() },
                            dismissCalendar = { uiEventDismissCalendar() },
                            onDateSelected = { uiEventSetDate(it) },
                        )
                    }
                }
            }
        },
    )
}
