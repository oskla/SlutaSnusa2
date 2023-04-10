package com.antisnusbolaget.slutasnusa2.di

import com.antisnusbolaget.slutasnusa2.data.room.UserDatabase
import com.antisnusbolaget.slutasnusa2.old.viewmodel.UserViewModel
import com.antisnusbolaget.slutasnusa2.repository.UserSettingsRepository
import com.antisnusbolaget.slutasnusa2.viewmodel.AchievementViewModel
import com.antisnusbolaget.slutasnusa2.viewmodel.HomeScreenViewModel
import com.antisnusbolaget.slutasnusa2.viewmodel.OnBoardingViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModel = module {
    viewModel { UserViewModel(get()) }
    viewModel { OnBoardingViewModel(userSettingsRepo = get()) }
    viewModel { HomeScreenViewModel(userSettingsRepository = get()) }
    viewModel { AchievementViewModel() }
}

val daoModule = module {
    single { UserDatabase.getInstance(androidContext()).userSettingsDao() }
}

val repositoryModule = module {
    single { UserSettingsRepository(userSettingsDao = get()) }
}

