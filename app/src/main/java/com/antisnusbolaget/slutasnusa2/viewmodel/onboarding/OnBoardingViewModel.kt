package com.antisnusbolaget.slutasnusa2.viewmodel.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.antisnusbolaget.slutasnusa2.data.DataStoreRepo
import com.antisnusbolaget.slutasnusa2.ui.screens.onboardingscreen.OnBoardingHelpers
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

            OnBoardingEvent.NavigateToNextView -> {
                navigateToNextView()
            }
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
            dataStoreRepo.getUserData().collect {
                updateLoadingState(loadingState = OnBoardingLoadingState.Loading) // TODO figure out how to make the repo tell us what state to show.

                updateUserData(
                    cost = it.costPerUnit,
                    units = it.units,
                    date = it.dateWhenQuit,
                )

                updateLoadingState(loadingState = OnBoardingLoadingState.Success)
            }
        }
    }

    private fun storeCostToDataStore() {
        updateLoadingState(OnBoardingLoadingState.Loading)
        viewModelScope.launch {
            dataStoreRepo.setCostPerUnit(cost = uiState.value.userData.costPerUnit)
        }
        updateLoadingState(OnBoardingLoadingState.Success)
    }

    private fun storeUnitsToDataStore() {
        updateLoadingState(OnBoardingLoadingState.Loading)

        viewModelScope.launch {
            dataStoreRepo.setAmountOfUnits(units = uiState.value.userData.units)
        }
        updateLoadingState(OnBoardingLoadingState.Success)
    }

    private fun storeQuitDateToDataStore() {
        updateLoadingState(OnBoardingLoadingState.Loading)

        viewModelScope.launch {
            dataStoreRepo.setDateWhenQuitInMillis(date = uiState.value.userData.dateWhenQuit)
        }
        updateLoadingState(OnBoardingLoadingState.Success)
    }

    private fun showCalendar(visible: Boolean) {
        val updatedVisibility = uiState.value.copy(isCalenderVisible = visible)
        _uiState.update { updatedVisibility }
    }

    private fun navigateToNextView() {
        when (uiState.value.currentView) {
            is OnBoardingNavigationView.CostView -> {
                storeCostToDataStore()
                _uiState.update { uiState.value.copy(currentView = OnBoardingNavigationView.UnitView) }
            }
            is OnBoardingNavigationView.UnitView -> {
                storeUnitsToDataStore()
                _uiState.update { uiState.value.copy(currentView = OnBoardingNavigationView.DateView) }
            }

            is OnBoardingNavigationView.DateView -> {
                storeQuitDateToDataStore()
            } // TODO Add actual navigation to Home
        }
    }

    private fun setCostPerUnit(cost: Int) {
        updateUserData(cost = cost)
        Timber.d("Cost per unit is set to: ${uiState.value.userData.costPerUnit}")
    }

    private fun setUnit(units: Int) {
        val newTotal = uiState.value.userData.units + units
        if (newTotal < 0) {
            Timber.d("Amounts of units is already 0, can't go lower: ${uiState.value.userData.units}")
            return
        }
        updateUserData(units = newTotal)
        Timber.d("Amounts of units is set to: ${uiState.value.userData.units}")
    }

    private fun setQuitDate(dateWhenQuit: Long) {
        val daysSinceQuit = OnBoardingHelpers().daysSinceQuit(dateWhenQuit)

        if (daysSinceQuit < 0) {
            Timber.d("Trying to set date in the future") // TODO add real error handling, this is checked in UI, but doesn't hurt to double check
            showCalendar(true)
            return
        }

        updateUserData(date = dateWhenQuit)
        Timber.d("Date when quit is set to: ${uiState.value}")
        Timber.d("Days since quit is: $daysSinceQuit")
    }

    private fun updateLoadingState(loadingState: OnBoardingLoadingState) {
        _uiState.update { uiState.value.copy(loadingState = loadingState) }
    }

    /** Function to safely update the value of userData in [_uiState] */
    private fun updateUserData(
        cost: Int? = null,
        units: Int? = null,
        date: Long? = null,
    ) {
        updateLoadingState(OnBoardingLoadingState.Loading)

        if (cost != null) {
            val updatedCost = uiState.value.userData.copy(costPerUnit = cost)
            _uiState.update { uiState.value.copy(userData = updatedCost) }
        }

        if (units != null) {
            val updatedUnits = uiState.value.userData.copy(units = units)
            _uiState.update { uiState.value.copy(userData = updatedUnits) }
            updateLoadingState(OnBoardingLoadingState.Success)
        }

        if (date != null) {
            val updatedDate = uiState.value.userData.copy(dateWhenQuit = date)
            _uiState.update { uiState.value.copy(userData = updatedDate) }
            updateLoadingState(OnBoardingLoadingState.Success)
        }

        updateLoadingState(OnBoardingLoadingState.Success)
    }
}
