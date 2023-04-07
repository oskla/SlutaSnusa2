package com.antisnusbolaget.slutasnusa2

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.antisnusbolaget.slutasnusa2.di.viewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainActivity : AppCompatActivity() {
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_main)
        startKoin {
            androidLogger()
            androidContext(applicationContext)
            koin.loadModules(listOf(viewModel))
        }

        setContent {
            RootComponent()
        }

        //      val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
//        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        //    val navController = navHostFragment.navController
        //  bottomNav.setupWithNavController(navController)
        // AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}
