package com.antisnusbolaget.slutasnusa2.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.antisnusbolaget.slutasnusa2.viewmodel.`interface`.OnBoardingEvent
import com.antisnusbolaget.slutasnusa2.viewmodel.`interface`.OnBoardingScreenState
import com.antisnusbolaget.slutasnusa2.viewmodel.`interface`.UserData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.concurrent.TimeUnit

class OnBoardingViewModel : ViewModel() {

    private val _userData: MutableState<UserData> = mutableStateOf(UserData())

    // uiState (what screen to show)
    private val _uiState = MutableStateFlow<OnBoardingScreenState>(OnBoardingScreenState.CostScreenState(_userData.value))
    val uiState: StateFlow<OnBoardingScreenState> = _uiState

    fun handleEvents(event: OnBoardingEvent) {
        when (event) {
            is OnBoardingEvent.SetCost -> setCostPerUnit(event.cost)
            is OnBoardingEvent.SetDate -> formatDate(event.date)
            is OnBoardingEvent.SetUnit -> setUnit(event.unitAmount)

            is OnBoardingEvent.NavigateToNextState -> navigateToNextState()
            // TODO handle backpress/swipe
        }
    }

    private fun navigateToNextState() {
        when (uiState.value) {
            is OnBoardingScreenState.CostScreenState -> {
                _uiState.value = OnBoardingScreenState.UnitScreenState(_userData.value) // TODO do we really need to pass the userSettings to every state? We could just fetch it in the UI from the viewmodel.
            }
            is OnBoardingScreenState.UnitScreenState -> {
                _uiState.value = OnBoardingScreenState.DateScreenState(_userData.value)
            }
            is OnBoardingScreenState.DateScreenState -> {} // TODO Add actual navigation to Home
        }
    }

    private fun setCostPerUnit(cost: Int) {
        _userData.value = _userData.value.copy(costPerUnit = cost)
    }

    private fun setUnit(units: Int) {
        _userData.value = _userData.value.copy(units = units)
    }

    private fun formatDate(dateWhenQuit: Long) {
        _userData.value = _userData.value.copy(dateWhenQuit = dateWhenQuit)
        println(dateWhenQuit)
        val date1millis = System.currentTimeMillis()

        val days = TimeUnit.MILLISECONDS.toDays(date1millis - dateWhenQuit)
    }
}
