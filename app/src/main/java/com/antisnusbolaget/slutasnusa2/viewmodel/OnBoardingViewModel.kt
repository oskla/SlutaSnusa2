package com.antisnusbolaget.slutasnusa2.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.antisnusbolaget.slutasnusa2.repository.UserSettingsRepository
import com.antisnusbolaget.slutasnusa2.viewmodel.`interface`.OnBoardingEvent
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class OnBoardingViewModel(private val userSettingsRepo: UserSettingsRepository) : ViewModel() {

    var costPerUnit = mutableStateOf(0)
        private set
    var amountOfUnits = mutableStateOf(0)
        private set
    var dateWhenQuit = mutableStateOf(0L)
        private set

    init {
        viewModelScope.launch {
            userSettingsRepo.setCost()
        }
        //saveCostPerUnitRoom(43)
    }

    fun handleEvents(event: OnBoardingEvent) {
        when (event) {
            is OnBoardingEvent.SetCost -> setCostPerUnit(event)
            is OnBoardingEvent.SetDate -> formatDate(event)
            is OnBoardingEvent.SetUnit -> setUnit(event)

            OnBoardingEvent.SaveCostToRoom -> saveCostPerUnitRoom(costPerUnit = costPerUnit.value)
        }
    }

    private fun saveCostPerUnitRoom(costPerUnit: Int) {
        viewModelScope.launch {
            userSettingsRepo.updateUserSettings(cost = costPerUnit)
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
