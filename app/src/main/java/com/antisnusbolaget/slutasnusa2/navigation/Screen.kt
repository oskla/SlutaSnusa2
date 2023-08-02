package com.antisnusbolaget.slutasnusa2.navigation

sealed class Screen(val route: String, var title: String) {
    object Home : Screen(route = "HOME_SCREEN", title = "Home")
    object Settings : Screen(route = "SETTINGS_SCREEN", title = "Settings")
    object Achievement : Screen(route = "ACHIEVEMENT_SCREEN", title = "Achievement")
    object OnBoarding : Screen(route = "ON_BOARDING_SCREEN", title = "OnBoarding")

    companion object {

        private val screensWithBottomNav = listOf(
            Home.route,
            Achievement.route,
        )

        fun shouldShowBottomBar(route: String?): Boolean {
            if (route.isNullOrBlank()) {
                return false
            }

            return screensWithBottomNav.any { route.contains(it, ignoreCase = true) }
        }
    }
}
