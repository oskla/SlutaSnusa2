package com.antisnusbolaget.slutasnusa2

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.antisnusbolaget.slutasnusa2.di.viewModel
import com.antisnusbolaget.slutasnusa2.ui.theme.SlutaSnutaTheme
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val moveToBackCallBack = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // Move the task containing this activity to the back of the activity stack
                moveTaskToBack(true)
            }
        }

        startKoin {
            androidLogger()
            androidContext(applicationContext)
            koin.loadModules(listOf(viewModel))
        }

        setContent {
            SlutaSnutaTheme {
                RootComponent(moveToBackCallBack = moveToBackCallBack)
            }
        }
    }
}
