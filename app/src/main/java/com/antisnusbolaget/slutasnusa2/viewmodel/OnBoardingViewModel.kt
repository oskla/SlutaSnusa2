package com.antisnusbolaget.slutasnusa2.viewmodel

import androidx.lifecycle.ViewModel
import com.antisnusbolaget.slutasnusa2.viewmodel.`interface`.OnBoardingEvent
import com.antisnusbolaget.slutasnusa2.viewmodel.`interface`.OnBoardingNavigationView
import com.antisnusbolaget.slutasnusa2.viewmodel.`interface`.UserData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.concurrent.TimeUnit

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
            is OnBoardingEvent.SetDate -> formatDate(event.date)
            is OnBoardingEvent.SetUnit -> setUnit(event.unitAmount)

            is OnBoardingEvent.NavigateToNextView -> navigateToNextView()
            // TODO handle backpress/swipe
        }
    }

    private fun navigateToNextView() {
        when (currentView.value) {
            is OnBoardingNavigationView.CostView -> {
                _currentView.value = OnBoardingNavigationView.UnitView
            }
            is OnBoardingNavigationView.UnitView -> {
                _currentView.value = OnBoardingNavigationView.DateView
            }
            is OnBoardingNavigationView.DateView -> {} // TODO Add actual navigation to Home
        }
    }

    private fun setCostPerUnit(cost: Int) {
        _userData.value = _userData.value.copy(costPerUnit = cost)
    }

    private fun setUnit(units: Int) {
        if (_userData.value.units + units < 0) {
            return
        }
        _userData.value = _userData.value.copy(units = _userData.value.units + units)
    }

    private fun formatDate(dateWhenQuit: Long) {
        _userData.value = _userData.value.copy(dateWhenQuit = dateWhenQuit)
        println(dateWhenQuit)
        val date1millis = System.currentTimeMillis()

        val days = TimeUnit.MILLISECONDS.toDays(date1millis - dateWhenQuit)
    }
}
