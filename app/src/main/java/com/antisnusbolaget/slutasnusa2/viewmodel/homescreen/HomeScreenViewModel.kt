package com.antisnusbolaget.slutasnusa2.viewmodel.homescreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.antisnusbolaget.slutasnusa2.data.DataStoreRepo
import com.antisnusbolaget.slutasnusa2.viewmodel.LoadingState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber

class HomeScreenViewModel(
    dataStoreRepo: DataStoreRepo,
) : ViewModel() {

    private val _uiState =
        MutableStateFlow<HomeState>(HomeState())
    val uiState: StateFlow<HomeState> = _uiState

    init {
        viewModelScope.launch {
            dataStoreRepo.isKeyStored().collect {
                if (it) {
                    Timber.d("Osk, is key stored? $it")
                    _uiState.update { uiState.value.copy(loadingState = LoadingState.SUCCESS) }
                } else {
                    Timber.d("Osk, is key stored? $it")
                    _uiState.update { uiState.value.copy(loadingState = LoadingState.FAILED) }
                }
            }
        }
    }
}
