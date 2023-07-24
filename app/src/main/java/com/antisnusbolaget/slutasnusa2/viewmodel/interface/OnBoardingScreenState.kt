package com.antisnusbolaget.slutasnusa2.viewmodel.`interface` // ktlint-disable package-name

sealed interface OnBoardingScreenState {
    data class CostScreenState(val userData: UserData) : OnBoardingScreenState
    data class UnitScreenState(val userData: UserData) : OnBoardingScreenState
    data class DateScreenState(val userData: UserData) : OnBoardingScreenState
}

data class UserData(
    val cost: Int = 0,
    val units: Int = 0,
    val dateWhenQuit: Long = 0,
)
