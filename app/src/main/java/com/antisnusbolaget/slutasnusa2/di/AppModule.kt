package com.antisnusbolaget.slutasnusa2.di

import com.antisnusbolaget.slutasnusa2.model.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModel = module {
    viewModel { UserViewModel(get()) }
}