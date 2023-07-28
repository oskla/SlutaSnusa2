package com.antisnusbolaget.slutasnusa2

import android.app.Application
import com.antisnusbolaget.slutasnusa2.di.viewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(viewModel)
        }
    }
}
