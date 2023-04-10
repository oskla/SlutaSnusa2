package com.antisnusbolaget.slutasnusa2.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.antisnusbolaget.slutasnusa2.repository.UserSettingsRepository
import kotlinx.coroutines.launch

class HomeScreenViewModel(private val userSettingsRepository: UserSettingsRepository) : ViewModel() {

    init {
        getUserSettingsRoom()
    }

    private fun getUserSettingsRoom() {
        viewModelScope.launch {
            userSettingsRepository.getUserSettings()
        }
    }
}
