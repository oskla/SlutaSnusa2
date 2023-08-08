package com.antisnusbolaget.slutasnusa2.viewmodel.homescreen

import com.antisnusbolaget.slutasnusa2.viewmodel.LoadingState
import com.antisnusbolaget.slutasnusa2.viewmodel.UserData

sealed interface HomeScreenEvent

data class HomeState(
    val loadingState: LoadingState = LoadingState.LOADING,
    var userData: UserData = UserData(0, 0, 0L),
)
