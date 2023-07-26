package com.antisnusbolaget.slutasnusa2.viewmodel

import androidx.lifecycle.ViewModel
import com.antisnusbolaget.slutasnusa2.ui.screens.onboardingscreen.OnBoardingHelpers
import com.antisnusbolaget.slutasnusa2.viewmodel.`interface`.OnBoardingEvent
import com.antisnusbolaget.slutasnusa2.viewmodel.`interface`.OnBoardingNavigationView
import com.antisnusbolaget.slutasnusa2.viewmodel.`interface`.OnBoardingState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import timber.log.Timber

class OnBoardingViewModel : ViewModel() {

    private val _uiState =
        MutableStateFlow<OnBoardingState>(OnBoardingState())
    val uiState: StateFlow<OnBoardingState> = _uiState

    fun handleEvents(event: OnBoardingEvent) {
        when (event) {
            is OnBoardingEvent.SetCost -> setCostPerUnit(event.cost)
            is OnBoardingEvent.SetDate -> setQuitDate(event.date)
            is OnBoardingEvent.SetUnit -> setUnit(event.unitAmount)

            is OnBoardingEvent.NavigateToNextView -> navigateToNextView()
            // TODO handle backpress/swipe
            OnBoardingEvent.DismissCalendar -> showCalendar(false)
            OnBoardingEvent.OpenCalendar -> showCalendar(true)
        }
    }

    private fun showCalendar(visible: Boolean) {
        _uiState.update { uiState.value.copy(isCalenderVisible = visible) }
    }

    private fun navigateToNextView() {
        when (uiState.value.currentView) {
            is OnBoardingNavigationView.CostView -> {
                _uiState.update { uiState.value.copy(currentView = OnBoardingNavigationView.UnitView) }
            }
            is OnBoardingNavigationView.UnitView -> {
                _uiState.update { uiState.value.copy(currentView = OnBoardingNavigationView.DateView) }
            }

            is OnBoardingNavigationView.DateView -> {} // TODO Add actual navigation to Home
        }
    }

    private fun setCostPerUnit(cost: Int) {
        _uiState.updateUserData(cost = cost)
        Timber.d("Cost per unit is set to: ${uiState.value.userData.costPerUnit}")
    }

    private fun setUnit(units: Int) {
        val newTotal = uiState.value.userData.units + units
        if (newTotal < 0) {
            Timber.d("Amounts of units is already 0, can't go lower: ${uiState.value.userData.units}")
            return
        }
        _uiState.updateUserData(units = newTotal)
        Timber.d("Amounts of units is set to: ${uiState.value.userData.units}")
    }

    private fun setQuitDate(dateWhenQuit: Long) {
        val daysSinceQuit = OnBoardingHelpers().daysSinceQuit(dateWhenQuit)

        if (daysSinceQuit < 0) {
            Timber.d("Trying to set date in the future") // TODO add real error handling, this is checked in UI, but doesn't hurt to double check
            showCalendar(true)
            return
        }

        _uiState.updateUserData(date = dateWhenQuit)
        Timber.d("Date when quit is set to: ${uiState.value.userData.dateWhenQuit}")
        Timber.d("Days since quit is: $daysSinceQuit")
    }

    /** Extension function to safely update the value of userData in [_uiState] */
    private fun MutableStateFlow<OnBoardingState>.updateUserData(
        cost: Int? = null,
        units: Int? = null,
        date: Long? = null,
    ) {
        if (cost != null) {
            val updatedCost = this.value.userData.copy(costPerUnit = cost)
            _uiState.update { uiState.value.copy(userData = updatedCost) }
        }

        if (units != null) {
            val updatedUnits = this.value.userData.copy(units = units)
            _uiState.update { uiState.value.copy(userData = updatedUnits) }
        }

        if (date != null) {
            val updatedDate = this.value.userData.copy(dateWhenQuit = date)
            _uiState.update { uiState.value.copy(userData = updatedDate) }
        }
    }
}
