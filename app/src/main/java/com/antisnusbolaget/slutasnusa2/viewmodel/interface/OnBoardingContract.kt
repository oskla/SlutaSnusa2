package com.antisnusbolaget.slutasnusa2.viewmodel.`interface` // ktlint-disable package-name

sealed interface OnBoardingEvent {
    data class SetCost(val cost: Int) : OnBoardingEvent
    data class SetUnit(val unitAmount: Int) : OnBoardingEvent
    data class SetDate(val date: Long) : OnBoardingEvent
    object NavigateToNextView : OnBoardingEvent
}

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
