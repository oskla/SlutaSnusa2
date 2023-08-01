package com.antisnusbolaget.slutasnusa2.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.antisnusbolaget.slutasnusa2.data.DataStoreRepo
import com.antisnusbolaget.slutasnusa2.ui.screens.onboardingscreen.OnBoardingHelpers
import com.antisnusbolaget.slutasnusa2.viewmodel.`interface`.OnBoardingEvent
import com.antisnusbolaget.slutasnusa2.viewmodel.`interface`.OnBoardingNavigationView
import com.antisnusbolaget.slutasnusa2.viewmodel.`interface`.OnBoardingState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber

class OnBoardingViewModel(
    private val dataStoreRepo: DataStoreRepo,
) : ViewModel() {

    private val _uiState =
        MutableStateFlow<OnBoardingState>(OnBoardingState())
    val uiState: StateFlow<OnBoardingState> = _uiState

    init {
        setUiStateFromDataStore()
    }

    fun handleEvents(event: OnBoardingEvent) {
        when (event) {
            is OnBoardingEvent.SetCost -> setCostPerUnit(event.cost)
            is OnBoardingEvent.SetDate -> setQuitDate(event.date)
            is OnBoardingEvent.SetUnit -> setUnit(event.unitAmount)

            OnBoardingEvent.NavigateToNextView -> navigateToNextView()
            OnBoardingEvent.NavigateBack -> navigateBack()

            OnBoardingEvent.DismissCalendar -> showCalendar(false)
            OnBoardingEvent.OpenCalendar -> showCalendar(true)
        }
    }

    private fun navigateBack() {
        when (uiState.value.currentView) {
            OnBoardingNavigationView.CostView -> {} // TODO should this kill activity? Or maybe put app in background?
            OnBoardingNavigationView.DateView -> {
                _uiState.update { uiState.value.copy(currentView = OnBoardingNavigationView.UnitView) }
            }
            OnBoardingNavigationView.UnitView -> {
                _uiState.update { uiState.value.copy(currentView = OnBoardingNavigationView.CostView) }
            }
        }
    }

    /** If you for some reason end up in OnBoarding even though you already have data stored.
     Then it would be nice to have that data prefilled. That's what this function is for. */
    private fun setUiStateFromDataStore() {
        viewModelScope.launch {
            dataStoreRepo.getCostPerUnit().collect {
                _uiState.updateUserData(cost = it.toInt())
            }
        }

        viewModelScope.launch {
            dataStoreRepo.getAmountOfUnits().collect {
                _uiState.updateUserData(units = it.toInt())
            }
        }

        viewModelScope.launch {
            dataStoreRepo.getDateWhenQuit().collect {
                _uiState.updateUserData(date = it.toLong())
            }
        }
    }

    private fun storeUserDataFromUiState() {
        // Update dataStore
        viewModelScope.launch {
            dataStoreRepo.setDateWhenQuitInMillis(date = uiState.value.userData.dateWhenQuit.toString())
        }

        viewModelScope.launch {
            dataStoreRepo.setCostPerUnit(cost = uiState.value.userData.costPerUnit.toString())
        }

        viewModelScope.launch {
            dataStoreRepo.setAmountOfUnits(units = uiState.value.userData.units.toString())
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

            is OnBoardingNavigationView.DateView -> {
                storeUserDataFromUiState()
            } // TODO Add actual navigation to Home
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
