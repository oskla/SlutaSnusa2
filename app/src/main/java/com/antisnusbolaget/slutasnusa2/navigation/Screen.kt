package com.antisnusbolaget.slutasnusa2.navigation

data class BooleanPair(val shouldShowNav: Boolean, val shouldShowYellow: Boolean)
sealed class Screen(val route: String, var title: String) {
    object Home : Screen(route = "home_screen", title = "Home")
    object Settings : Screen(route = "settings_screen", title = "Settings")
    object Unit : Screen(route = "unit_screen", title = "Unit")
    object Cost : Screen(route = "cost_screen", title = "Cost")
    object Date : Screen(route = "date_screen", title = "Date")
    object Achievement : Screen(route = "achievement_screen", title = "Achievement")

    companion object {
        fun shouldShowBottomBar(route: String?): BooleanPair {
            val screensWithBottomYellow = listOf(
                Unit.route,
                Cost.route,
                Date.route,
            )

            val screensWithBottomNav = listOf(
                Home.route,
                Achievement.route,
            )

            if (route.isNullOrBlank()) return BooleanPair(shouldShowNav = false, shouldShowYellow = false)

            val showBottomNav = screensWithBottomNav.any { route.contains(it, ignoreCase = true) }
            val showBottomYellow = screensWithBottomYellow.any { route.contains(it, ignoreCase = true) }

            return BooleanPair(showBottomNav, showBottomYellow)
        }
    }
}
