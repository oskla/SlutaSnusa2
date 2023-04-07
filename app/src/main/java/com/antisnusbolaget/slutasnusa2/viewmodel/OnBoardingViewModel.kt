package com.antisnusbolaget.slutasnusa2.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.antisnusbolaget.slutasnusa2.viewmodel.`interface`.Event
import com.antisnusbolaget.slutasnusa2.viewmodel.`interface`.OnBoardingActions

class OnBoardingViewModel : ViewModel() {

    var costPerUnit = mutableStateOf(0)
        private set
    var amountOfUnits = mutableStateOf(0)
        private set
    var dateWhenQuit = mutableStateOf("2023-01-12")
        private set

    fun handleAction(action: Event) {
        when (action) {
            is OnBoardingActions.SetCost -> setCostPerUnit(action)
            is OnBoardingActions.SetDate -> formatDate(action)
            is OnBoardingActions.SetUnit -> setUnit(action)
        }
    }

    private fun setCostPerUnit(action: OnBoardingActions.SetCost) {
        costPerUnit.value = action.cost
    }

    private fun setUnit(action: OnBoardingActions.SetUnit) {
        amountOfUnits.value = action.unitAmount
    }

    private fun formatDate(action: OnBoardingActions.SetDate) {
        dateWhenQuit.value = action.date
    }
}
