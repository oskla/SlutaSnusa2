package com.antisnusbolaget.slutasnusa2.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.antisnusbolaget.slutasnusa2.ui.screens.HomeScreen
import com.antisnusbolaget.slutasnusa2.ui.screens.SettingScreen
import com.antisnusbolaget.slutasnusa2.ui.screens.mainscreen.achievementscreen.AchievementScreen
import com.antisnusbolaget.slutasnusa2.ui.screens.onboardingscreen.OnBoardingScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.OnBoarding.route,
    ) {
        composable(Screen.OnBoarding.route) {
            OnBoardingScreen()
        }

        navigation(
            startDestination = "LEFT_TAB",
            route = "TABS",
        ) {
            navigation(
                startDestination = Screen.Home.route,
                route = "LEFT_TAB",
            ) {
                composable(Screen.Home.route) {
                    HomeScreen()
                }
                composable(Screen.Settings.route) {
                    SettingScreen()
                }
            }

            navigation(
                startDestination = Screen.Achievement.route,
                route = "RIGHT_TAB",
            ) {
                composable(Screen.Achievement.route) {
                    AchievementScreen()
                }
            }
        }
    }
}
