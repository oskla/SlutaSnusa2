package com.antisnusbolaget.slutasnusa2.di

import com.antisnusbolaget.slutasnusa2.viewmodel.AchievementViewModel
import com.antisnusbolaget.slutasnusa2.viewmodel.HomeScreenViewModel
import com.antisnusbolaget.slutasnusa2.viewmodel.OnBoardingViewModel
import com.antisnusbolaget.slutasnusa2.old.viewmodel.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModel = module {
    viewModel { UserViewModel(get()) }
    viewModel { OnBoardingViewModel() }
    viewModel { HomeScreenViewModel() }
    viewModel { AchievementViewModel() }
}
