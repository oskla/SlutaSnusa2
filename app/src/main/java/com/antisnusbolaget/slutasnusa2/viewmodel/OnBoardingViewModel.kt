package com.antisnusbolaget.slutasnusa2.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.antisnusbolaget.slutasnusa2.viewmodel.`interface`.OnBoardingEvent
import com.antisnusbolaget.slutasnusa2.viewmodel.`interface`.OnBoardingState
import java.util.concurrent.TimeUnit

class OnBoardingViewModel : ViewModel() {

    val state = mutableStateOf(OnBoardingState())

    fun handleEvents(event: OnBoardingEvent) {
        when (event) {
            is OnBoardingEvent.SetCost -> setCostPerUnit(event)
            is OnBoardingEvent.SetDate -> formatDate(event)
            is OnBoardingEvent.SetUnit -> setUnit(event)
        }
    }

    private fun setCostPerUnit(action: OnBoardingEvent.SetCost) {
        state.value = state.value.copy(costPerUnit = action.cost)
    }

    private fun setUnit(action: OnBoardingEvent.SetUnit) {
        state.value = state.value.copy(amountOfUnits = action.unitAmount)
    }

    private fun formatDate(action: OnBoardingEvent.SetDate) {
        state.value = state.value.copy(dateWhenQuick = action.date)
        val date1millis = System.currentTimeMillis()

        val days = TimeUnit.MILLISECONDS.toDays(date1millis - action.date)
    }
}
