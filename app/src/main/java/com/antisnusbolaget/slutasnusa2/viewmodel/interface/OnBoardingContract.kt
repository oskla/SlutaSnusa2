package com.antisnusbolaget.slutasnusa2.viewmodel.`interface` // ktlint-disable package-name

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
    val userData: UserData = UserData(),
    val isCalenderVisible: Boolean = false,
    val currentView: OnBoardingNavigationView = OnBoardingNavigationView.CostView,
)

sealed interface OnBoardingNavigationView {
    object CostView : OnBoardingNavigationView
    object UnitView : OnBoardingNavigationView
    object DateView : OnBoardingNavigationView
}

data class UserData(
    val costPerUnit: Int = 0,
    val units: Int = 0,
    val dateWhenQuit: Long = 0,
)
