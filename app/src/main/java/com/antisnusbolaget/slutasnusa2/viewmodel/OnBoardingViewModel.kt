package com.antisnusbolaget.slutasnusa2.viewmodel

import androidx.lifecycle.ViewModel
import com.antisnusbolaget.slutasnusa2.ui.screens.onboardingscreen.OnBoardingHelpers
import com.antisnusbolaget.slutasnusa2.viewmodel.`interface`.OnBoardingEvent
import com.antisnusbolaget.slutasnusa2.viewmodel.`interface`.OnBoardingNavigationView
import com.antisnusbolaget.slutasnusa2.viewmodel.`interface`.UserData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import timber.log.Timber

class OnBoardingViewModel : ViewModel() {

    private val _userData: MutableStateFlow<UserData> = MutableStateFlow(UserData())
    val userData: StateFlow<UserData> = _userData

    // uiState (what screen to show)
    private val _currentView =
        MutableStateFlow<OnBoardingNavigationView>(OnBoardingNavigationView.CostView)
    val currentView: StateFlow<OnBoardingNavigationView> = _currentView

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
        if (currentView.value is OnBoardingNavigationView.DateView) {
            val showCalendar = (currentView.value as OnBoardingNavigationView.DateView).copy(showCalendar = visible)
            _currentView.value = showCalendar
        }
    }

    private fun navigateToNextView() {
        when (currentView.value) {
            is OnBoardingNavigationView.CostView -> {
                _currentView.value = OnBoardingNavigationView.UnitView
            }
            is OnBoardingNavigationView.UnitView -> {
                _currentView.value = OnBoardingNavigationView.DateView(showCalendar = false)
            }
            is OnBoardingNavigationView.DateView -> {} // TODO Add actual navigation to Home
        }
    }

    private fun setCostPerUnit(cost: Int) {
        _userData.value = _userData.value.copy(costPerUnit = cost)
        Timber.d("Cost per unit is set to: ${_userData.value.costPerUnit}")
    }

    private fun setUnit(units: Int) {
        if (_userData.value.units + units < 0) {
            Timber.d("Amounts of units is already 0, can't go lower: ${_userData.value.units}")
            return
        }
        _userData.value = _userData.value.copy(units = _userData.value.units + units)
        Timber.d("Amounts of units is set to: ${_userData.value.units}")
    }

    private fun setQuitDate(dateWhenQuit: Long) {
        val daysSinceQuit = OnBoardingHelpers().daysSinceQuit(dateWhenQuit)

        if (daysSinceQuit < 0) {
            Timber.d("Trying to set date in the future") // TODO add real error handling, this is checked in UI, but doesn't hurt to double check
            showCalendar(true)
            return
        }

        _userData.value = _userData.value.copy(dateWhenQuit = dateWhenQuit)

        Timber.d("Date when quit is set to: ${_userData.value.dateWhenQuit}")
        Timber.d("Days since quit is: $daysSinceQuit")
    }
}
