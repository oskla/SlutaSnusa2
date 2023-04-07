package com.antisnusbolaget.slutasnusa2.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.antisnusbolaget.slutasnusa2.viewmodel.`interface`.OnBoardingActions
import java.text.DateFormat

class OnBoardingViewModel : ViewModel() {

    var costPerUnit = mutableStateOf(0)
        private set
    var amountOfUnits = mutableStateOf(0)
        private set
    var dateWhenQuit = mutableStateOf(DateFormat.DATE_FIELD)
        private set

    fun checkUiAction(action: OnBoardingActions) {
        when (action) {
            is OnBoardingActions.SetCost -> TODO()
            is OnBoardingActions.SetDate -> TODO()
            is OnBoardingActions.SetUnit -> TODO()
        }
    }
}
