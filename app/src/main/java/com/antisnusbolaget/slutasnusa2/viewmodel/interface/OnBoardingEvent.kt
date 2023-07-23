package com.antisnusbolaget.slutasnusa2.viewmodel.`interface` // ktlint-disable package-name

sealed interface OnBoardingEvent {
    data class SetCost(val cost: Int) : OnBoardingEvent
    data class SetUnit(val unitAmount: Int) : OnBoardingEvent
    data class SetDate(val date: Long) : OnBoardingEvent
    object NavigateToNextState : OnBoardingEvent
}
