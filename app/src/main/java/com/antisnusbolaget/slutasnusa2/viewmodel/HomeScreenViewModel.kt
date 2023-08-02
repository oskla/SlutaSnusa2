package com.antisnusbolaget.slutasnusa2.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.antisnusbolaget.slutasnusa2.data.DataStoreRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

sealed interface HomeScreenLoadingState {
    object Loading : HomeScreenLoadingState
    object Failed : HomeScreenLoadingState
    object Success : HomeScreenLoadingState
}

class HomeScreenViewModel(
    dataStoreRepo: DataStoreRepo,
) : ViewModel() {

    private val _uiState: MutableStateFlow<HomeScreenLoadingState> = MutableStateFlow(HomeScreenLoadingState.Loading)
    val uiState: StateFlow<HomeScreenLoadingState> = _uiState

    init {
        viewModelScope.launch {
            dataStoreRepo.isKeyStored().collect {
                if (it) {
                    Timber.d("Osk, is key stored? $it")
                    _uiState.value = HomeScreenLoadingState.Success
                } else {
                    Timber.d("Osk, is key stored? $it")
                    _uiState.value = HomeScreenLoadingState.Failed
                }
            }
        }
    }
}
