package com.antisnusbolaget.slutasnusa2.navigation

import androidx.activity.OnBackPressedCallback
import androidx.compose.runtime.mutableStateOf

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

        private fun handleBackAndForwardNavigation(): String {
            return when (onBoardingScreenIndex.value) {
                0 -> Cost.route
                1 -> Unit.route
                2 -> Date.route
                else -> Home.route
            }
        }

        fun onBackPressed(moveToBack: OnBackPressedCallback): String {
            if (onBoardingScreenIndex.value <= 0) {
                onBoardingScreenIndex.value = 0 // för att säkerställa att det verkligen aldrig blir mindre än 0
                moveToBack.handleOnBackPressed()
            }
            onBoardingScreenIndex.value = onBoardingScreenIndex.value - 1
            return handleBackAndForwardNavigation()
        }

        fun nextScreen(): String {
            onBoardingScreenIndex.value += 1
            return handleBackAndForwardNavigation()
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
