package com.antisnusbolaget.slutasnusa2.viewmodel.`interface` // ktlint-disable package-name

sealed interface OnBoardingScreenState {
    object CostScreenState : OnBoardingScreenState
    object UnitScreenState : OnBoardingScreenState
    object DateScreenState : OnBoardingScreenState
}
