package com.antisnusbolaget.slutasnusa2.navigation

sealed class Screen(val route: String, var title: String) {
    object Home : Screen(route = "home_screen", title = "Home")
    object Settings : Screen(route = "settings_screen", title = "Settings")
    object Achievement : Screen(route = "achievement_screen", title = "Achievement")
    object OnBoarding : Screen(route = "onboarding_screen", title = "Onboarding")

    companion object {
        private val screenWithTopBar = OnBoarding.route

        fun shouldShowTopBar(route: String?): Boolean {
            if (route.isNullOrBlank()) return false
            return screenWithTopBar == route
        }
    }
}
