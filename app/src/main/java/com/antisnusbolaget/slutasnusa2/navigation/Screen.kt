package com.antisnusbolaget.slutasnusa2.navigation

data class BottomBarVisibility(val shouldShowNav: Boolean, val shouldShowYellow: Boolean)
sealed class Screen(val route: String, var title: String) {
    object Home : Screen(route = "home_screen", title = "Home")
    object Settings : Screen(route = "settings_screen", title = "Settings")
    object Unit : Screen(route = "unit_screen", title = "Unit")
    object Cost : Screen(route = "cost_screen", title = "Cost")
    object Date : Screen(route = "date_screen", title = "Date")
    object Achievement : Screen(route = "achievement_screen", title = "Achievement")

    companion object {
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

            if (route.isNullOrBlank()) return BottomBarVisibility(shouldShowNav = false, shouldShowYellow = false)

            val showBottomNav = screensWithBottomNav.any { route.contains(it, ignoreCase = true) }
            val showBottomYellow = screensWithBottomYellow.any { route.contains(it, ignoreCase = true) }

            return BottomBarVisibility(showBottomNav, showBottomYellow)
        }
    }
}
