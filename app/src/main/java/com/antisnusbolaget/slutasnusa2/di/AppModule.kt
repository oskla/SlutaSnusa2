package com.antisnusbolaget.slutasnusa2.di

import com.antisnusbolaget.slutasnusa2.data.DataStoreRepo
import com.antisnusbolaget.slutasnusa2.viewmodel.AchievementViewModel
import com.antisnusbolaget.slutasnusa2.viewmodel.HomeScreenViewModel
import com.antisnusbolaget.slutasnusa2.viewmodel.onboarding.OnBoardingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModel = module {
    viewModel { OnBoardingViewModel(dataStoreRepo = get()) }
    viewModel { HomeScreenViewModel() }
    viewModel { AchievementViewModel() }
    single { DataStoreRepo(context = get()) }
}
