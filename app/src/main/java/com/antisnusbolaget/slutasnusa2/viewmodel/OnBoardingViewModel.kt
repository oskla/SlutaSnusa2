package com.antisnusbolaget.slutasnusa2.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.antisnusbolaget.slutasnusa2.viewmodel.`interface`.OnBoardingEvent
import com.antisnusbolaget.slutasnusa2.viewmodel.`interface`.OnBoardingScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.concurrent.TimeUnit

class OnBoardingViewModel : ViewModel() {

    var costPerUnit = mutableStateOf(0)
        private set
    var amountOfUnits = mutableStateOf(0)
        private set
    var dateWhenQuit = mutableStateOf(0L)
        private set

    // uiState (what screen to show)
    private val _uiState =
        MutableStateFlow<OnBoardingScreenState>(OnBoardingScreenState.CostScreenState)
    val uiState: StateFlow<OnBoardingScreenState> = _uiState

    fun handleEvents(event: OnBoardingEvent) {
        when (event) {
            is OnBoardingEvent.SetCost -> setCostPerUnit(event)
            is OnBoardingEvent.SetDate -> formatDate(event)
            is OnBoardingEvent.SetUnit -> setUnit(event)

            is OnBoardingEvent.NavigateToNextState -> navigateToNextState()
        }
    }

    private fun navigateToNextState() {
        when (uiState.value) {
            OnBoardingScreenState.CostScreenState -> { _uiState.value = OnBoardingScreenState.UnitScreenState }
            OnBoardingScreenState.UnitScreenState -> { _uiState.value = OnBoardingScreenState.DateScreenState }
            OnBoardingScreenState.DateScreenState -> {} // TODO Add actual navigation to Home
        }
    }

    private fun setCostPerUnit(action: OnBoardingEvent.SetCost) {
        costPerUnit.value = action.cost
    }

    private fun setUnit(action: OnBoardingEvent.SetUnit) {
        amountOfUnits.value = action.unitAmount
    }

    private fun formatDate(action: OnBoardingEvent.SetDate) {
        dateWhenQuit.value = action.date
        println(dateWhenQuit.value)
        val date1millis = System.currentTimeMillis()

        val days = TimeUnit.MILLISECONDS.toDays(date1millis - action.date)
    }
}
