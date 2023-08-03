package com.antisnusbolaget.slutasnusa2.ui.screens.onboardingscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.antisnusbolaget.slutasnusa2.navigation.Screen
import com.antisnusbolaget.slutasnusa2.ui.components.TopBar
import com.antisnusbolaget.slutasnusa2.ui.screens.onboardingscreen.views.BottomNavOnBoarding
import com.antisnusbolaget.slutasnusa2.viewmodel.LoadingState
import com.antisnusbolaget.slutasnusa2.viewmodel.onboarding.OnBoardingEvent
import com.antisnusbolaget.slutasnusa2.viewmodel.onboarding.OnBoardingNavigationView
import com.antisnusbolaget.slutasnusa2.viewmodel.onboarding.OnBoardingState
import com.antisnusbolaget.slutasnusa2.viewmodel.onboarding.OnBoardingViewModel
import org.koin.androidx.compose.koinViewModel

const val ON_BOARDING_VERTICAL_PADDING = 24
const val ON_BOARDING_HORIZONTAL_PADDING = 32

@Composable
fun OnBoardingScreen(
    viewModel: OnBoardingViewModel = koinViewModel(),
    navController: NavController,
) {
    val uiState = viewModel.uiState.collectAsState()

    when (uiState.value.loadingState) {
        LoadingState.FAILED -> TODO()
        LoadingState.LOADING -> {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator()
            }
        }
        LoadingState.SUCCESS -> {
            OnBoardingContent(
                viewModel = viewModel,
                uiState = uiState,
                navController = navController,
            )
        }
    }
}

@Composable
private fun OnBoardingContent(
    viewModel: OnBoardingViewModel,
    uiState: State<OnBoardingState>,
    navController: NavController,
) {
    fun uiEventSetCostPerUnit(cost: Int) = viewModel.handleEvents(OnBoardingEvent.SetCost(cost))
    fun uiEventSetUnits(units: Int) = viewModel.handleEvents(OnBoardingEvent.SetUnit(units))
    fun uiEventOpenCalendar() = viewModel.handleEvents(OnBoardingEvent.OpenCalendar)
    fun uiEventDismissCalendar() = viewModel.handleEvents(OnBoardingEvent.DismissCalendar)
    fun uiEventSetDate(date: Long) = viewModel.handleEvents(OnBoardingEvent.SetDate(date))
    fun uiEventNavigateBack() = viewModel.handleEvents(OnBoardingEvent.NavigateBack)
    fun uiEventNavigateNext() = viewModel.handleEvents(OnBoardingEvent.NavigateToNextView)

    Scaffold(
        topBar = { TopBar() },
        bottomBar = {
            BottomNavOnBoarding(
                onClickNext = {
                    if (uiState.value.currentView == OnBoardingNavigationView.DateView) {
                        navController.navigate(Screen.Home.route)
                    }
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
                        sliderValue = uiState.value.userData.costPerUnit.toFloat(),
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
