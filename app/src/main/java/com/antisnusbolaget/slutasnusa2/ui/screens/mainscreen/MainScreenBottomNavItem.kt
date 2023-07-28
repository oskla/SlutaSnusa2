package com.antisnusbolaget.slutasnusa2.ui.screens.mainscreen

import com.antisnusbolaget.slutasnusa2.R

sealed class MainScreenBottomNavItem(var title: String, var icon: Int, var screen_route: String) {
    object Home : MainScreenBottomNavItem("Home", R.drawable.ic_home_foreground, "LEFT_TAB")
    object Achievement : MainScreenBottomNavItem("Achievement", R.drawable.ic_achievement, "RIGHT_TAB")
}
