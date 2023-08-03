package com.antisnusbolaget.slutasnusa2.viewmodel.homescreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.antisnusbolaget.slutasnusa2.data.DataStoreRepo
import com.antisnusbolaget.slutasnusa2.viewmodel.LoadingState
import com.antisnusbolaget.slutasnusa2.viewmodel.UserData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber

class HomeScreenViewModel(
    private val dataStoreRepo: DataStoreRepo,
) : ViewModel() {

    private val _uiState =
        MutableStateFlow<HomeState>(HomeState())
    val uiState: StateFlow<HomeState> = _uiState

    init {
        viewModelScope.launch {
            dataStoreRepo.isKeyStored().collect {
                if (it) {
                    Timber.d("Osk, is key stored? $it")
                    setDataToUi()
                } else {
                    Timber.d("Osk, is key stored? $it")
                    _uiState.update { uiState.value.copy(loadingState = LoadingState.FAILED) }
                }
            }
        }
    }

    private fun setDataToUi() {
        viewModelScope.launch {
            dataStoreRepo.getUserData().collect { it ->
                _uiState.update {
                    uiState.value.copy(
                        userData = UserData(
                            costPerUnit = it.userData.costPerUnit,
                            units = it.userData.units,
                            dateWhenQuit = it.userData.dateWhenQuit,
                        ),
                        loadingState = LoadingState.SUCCESS,
                    )
                }
                Timber.d("Osk, ${it.costPerUnit}")
            }
        }
    }
}
