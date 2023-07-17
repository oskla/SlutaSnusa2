package com.antisnusbolaget.slutasnusa2.navigation

import androidx.activity.OnBackPressedCallback
import androidx.compose.runtime.mutableStateOf
import androidx.navigation.NavController

data class BottomBarVisibility(val isHomeScreen: Boolean, val isOnBoarding: Boolean)

sealed class Screen(val route: String, var title: String) {
    object Home : Screen(route = "home_screen", title = "Home")
    object Settings : Screen(route = "settings_screen", title = "Settings")
    object Unit : Screen(route = "unit_screen", title = "Unit")
    object Cost : Screen(route = "cost_screen", title = "Cost")
    object Date : Screen(route = "date_screen", title = "Date")
    object Achievement : Screen(route = "achievement_screen", title = "Achievement")

    companion object {

        private val onBoardingScreenIndex = mutableStateOf(0)

        private fun handleBackAndForwardNavigation(navController: NavController) {
            when (onBoardingScreenIndex.value) {
                0 -> navController.navigate(Cost.route) // Cost.route
                1 -> navController.navigate(Unit.route) // Unit.route
                2 -> navController.navigate(Date.route)
                else -> {
                    navController.navigate(Home.route) {
                        popUpTo(ON_BOARDING_GRAPH_ROUTE) {
                            inclusive = true
                        }
                    }
                }
            }
        }

        fun onBackPressed(moveToBack: OnBackPressedCallback, navController: NavController) {
            if (onBoardingScreenIndex.value <= 0) {
                onBoardingScreenIndex.value = 0 // för att säkerställa att det verkligen aldrig blir mindre än 0
                moveToBack.handleOnBackPressed()
                return
            }
            onBoardingScreenIndex.value = onBoardingScreenIndex.value - 1
            handleBackAndForwardNavigation(navController)
        }

        fun nextScreen(navController: NavController) {
            onBoardingScreenIndex.value += 1
            handleBackAndForwardNavigation(navController)
        }

        private val screensWithTopBar = listOf(
            Cost.route,
            Unit.route,
            Date.route,
        )

        private val screensWithBottomNav = listOf(
            Home.route,
            Achievement.route,
        )

        fun shouldShowTopBar(route: String?): Boolean {
            if (route.isNullOrBlank()) return false
            return screensWithTopBar.any { route.contains(it, ignoreCase = true) }
        }

        fun shouldShowBottomBar(route: String?): BottomBarVisibility {
            val screensWithBottomYellow = listOf(
                Unit.route,
                Cost.route,
                Date.route,
            )

            if (route.isNullOrBlank()) {
                return BottomBarVisibility(
                    isHomeScreen = false,
                    isOnBoarding = false,
                )
            }

            val showBottomNav = screensWithBottomNav.any { route.contains(it, ignoreCase = true) }
            val showBottomYellow =
                screensWithBottomYellow.any { route.contains(it, ignoreCase = true) }

            return BottomBarVisibility(showBottomNav, showBottomYellow)
        }
    }
}
