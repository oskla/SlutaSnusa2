package com.antisnusbolaget.slutasnusa2.viewmodel.`interface`

import java.util.*

sealed interface OnBoardingActions {
    data class SetCost(val cost: Int) : OnBoardingActions
    data class SetUnit(val unitAmount: Int) : OnBoardingActions
    data class SetDate(val date: Date) : OnBoardingActions
}
