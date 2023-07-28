package com.antisnusbolaget.slutasnusa2.ui.screens.mainscreen

import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun MainScreenBottomNav(navController: NavController) {
    val items = listOf(
        MainScreenBottomNavItem.Home,
        MainScreenBottomNavItem.Achievement,
    )
}
