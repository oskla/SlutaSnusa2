package com.antisnusbolaget.slutasnusa2.ui.screens.mainscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.antisnusbolaget.slutasnusa2.R
import com.antisnusbolaget.slutasnusa2.navigation.MainNavGraph
import com.antisnusbolaget.slutasnusa2.navigation.Screen

@Composable
fun MainScreen(navController: NavController) {
    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                items = listOf(
                    BottomNavItem(
                        name = Screen.Home.title,
                        route = Screen.Home.route,
                        icon = R.drawable.ic_home_foreground,
                    ),
                    BottomNavItem(
                        name = Screen.Achievement.title,
                        route = Screen.Achievement.route,
                        icon = R.drawable.ic_achievement,
                    ),
                ),
                navController = navController,
                onItemClick = {
                    navController.navigate(it.route) {
                        popUpTo(it.route) {
                            inclusive = true
                        }
                    }
                },
            )
        },
        content = { paddingValues ->
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(paddingValues),
            ) {
                MainNavGraph(navController = navController as NavHostController)
            }
        },
    )
}
