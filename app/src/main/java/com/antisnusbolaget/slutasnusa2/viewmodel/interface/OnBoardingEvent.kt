package com.antisnusbolaget.slutasnusa2.viewmodel.`interface` // ktlint-disable package-name

sealed interface OnBoardingEvent {
    data class SetCost(val cost: Int) : OnBoardingEvent
    data class SetUnit(val unitAmount: Int) : OnBoardingEvent
    data class SetDate(val date: Long) : OnBoardingEvent
}

data class OnBoardingState(
    val costPerUnit: Int = 0,
    val amountOfUnits: Int = 0,
    val dateWhenQuick: Long = 0L,
)
