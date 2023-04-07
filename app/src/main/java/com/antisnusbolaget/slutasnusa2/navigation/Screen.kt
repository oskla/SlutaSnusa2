package com.antisnusbolaget.slutasnusa2.navigation

sealed class Screen(val route: String, var title: String) {
    object Home : Screen(route = "home_screen", title = "Home")
    object Settings : Screen(route = "settings_screen", title = "Settings")
    object Unit : Screen(route = "unit_screen", title = "Unit")
    object Cost : Screen(route = "cost_screen", title = "Cost")
    object Date : Screen(route = "date_screen", title = "Date")
    object Achievement : Screen(route = "achievement_screen", title = "Achievement")
}
