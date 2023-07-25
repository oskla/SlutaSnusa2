package com.antisnusbolaget.slutasnusa2

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.antisnusbolaget.slutasnusa2.di.viewModel
import com.antisnusbolaget.slutasnusa2.ui.theme.SlutaSnutaTheme
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidLogger()
            androidContext(applicationContext)
            koin.loadModules(listOf(viewModel))
        }

        setContent {
            SlutaSnutaTheme {
                RootComponent()
            }
        }
    }
}
