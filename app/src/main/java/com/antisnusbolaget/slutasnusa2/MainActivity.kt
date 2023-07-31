package com.antisnusbolaget.slutasnusa2

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.antisnusbolaget.slutasnusa2.ui.theme.SlutaSnutaTheme
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        setContent {
            SlutaSnutaTheme {
                RootComponent()
            }
        }
    }
}
