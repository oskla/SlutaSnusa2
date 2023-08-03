package com.antisnusbolaget.slutasnusa2.viewmodel.onboarding

import com.antisnusbolaget.slutasnusa2.viewmodel.LoadingState
import com.antisnusbolaget.slutasnusa2.viewmodel.UserData

// ktlint-disable package-name

sealed interface OnBoardingEvent {
    // Cost view
    data class SetCost(val cost: Int) : OnBoardingEvent

    // Unit view
    data class SetUnit(val unitAmount: Int) : OnBoardingEvent

    // Date view
    data class SetDate(val date: Long) : OnBoardingEvent
    object OpenCalendar : OnBoardingEvent
    object DismissCalendar : OnBoardingEvent

    // All views
    object NavigateToNextView : OnBoardingEvent
    object NavigateBack : OnBoardingEvent
}

data class OnBoardingState(
    val userData: UserData = UserData(0, 0, 0L),
    val isCalenderVisible: Boolean = false,
    val currentView: OnBoardingNavigationView = OnBoardingNavigationView.CostView,
    val loadingState: LoadingState = LoadingState.LOADING,
)

sealed interface OnBoardingNavigationView {
    object CostView : OnBoardingNavigationView
    object UnitView : OnBoardingNavigationView
    object DateView : OnBoardingNavigationView
}
