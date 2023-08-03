package com.antisnusbolaget.slutasnusa2.viewmodel.homescreen

import com.antisnusbolaget.slutasnusa2.viewmodel.LoadingState

sealed interface HomeScreenEvent

data class HomeState(
    val loadingState: LoadingState = LoadingState.LOADING,
)
