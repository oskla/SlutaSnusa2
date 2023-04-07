package com.antisnusbolaget.slutasnusa2.viewmodel.`interface` // ktlint-disable package-name

sealed interface OnBoardingActions : Event {
    data class SetCost(val cost: Int) : OnBoardingActions
    data class SetUnit(val unitAmount: Int) : OnBoardingActions
    data class SetDate(val date: String) : OnBoardingActions
}
