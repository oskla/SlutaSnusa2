package com.antisnusbolaget.slutasnusa2.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.antisnusbolaget.slutasnusa2.ui.screens.HomeScreen
import com.antisnusbolaget.slutasnusa2.ui.screens.SettingScreen
import com.antisnusbolaget.slutasnusa2.ui.screens.mainscreen.achievementscreen.AchievementScreen
import com.antisnusbolaget.slutasnusa2.ui.screens.onboardingscreen.OnBoardingScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
    ) {
        composable(Screen.OnBoarding.route) {
            OnBoardingScreen()
        }
        composable(Screen.Home.route) {
            HomeScreen()
        }
        composable(Screen.Settings.route) {
            SettingScreen()
        }
        composable(Screen.Achievement.route) {
            AchievementScreen()
        }
    }
}
