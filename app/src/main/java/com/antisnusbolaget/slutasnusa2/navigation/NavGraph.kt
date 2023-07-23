package com.antisnusbolaget.slutasnusa2.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.antisnusbolaget.slutasnusa2.ui.screens.AchievementScreen
import com.antisnusbolaget.slutasnusa2.ui.screens.HomeScreen
import com.antisnusbolaget.slutasnusa2.ui.screens.OnBoardingScreen
import com.antisnusbolaget.slutasnusa2.ui.screens.SettingScreen

const val HOME_ROUTE = "home_route"

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Onboarding.route,
    ) {
        composable(Screen.Onboarding.route) {
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
