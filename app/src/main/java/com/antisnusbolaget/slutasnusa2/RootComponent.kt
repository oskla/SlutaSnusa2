package com.antisnusbolaget.slutasnusa2

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.antisnusbolaget.slutasnusa2.navigation.NavGraph
import com.antisnusbolaget.slutasnusa2.navigation.Screen
import com.antisnusbolaget.slutasnusa2.ui.screens.mainscreen.BottomNavItem
import com.antisnusbolaget.slutasnusa2.ui.screens.mainscreen.BottomNavigationBar

@Composable
fun RootComponent() {
    val navController = rememberNavController()

    val bottomBarState = remember {
        mutableStateOf(false)
    }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    bottomBarState.value = Screen.shouldShowBottomBar(navBackStackEntry?.destination?.route)

    val bottomNavList = listOf(
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
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
    ) {
        Scaffold(
            bottomBar = {
                if (bottomBarState.value) {
                    BottomNavigationBar(
                        items = bottomNavList,
                        navController = navController,
                        onItemClick = {
                            navController.navigate(it.route) {
                                popUpTo(it.route) {
                                    inclusive = true
                                }
                            }
                        },
                    )
                }
            },
            content = { paddingValues ->
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .padding(paddingValues),
                ) {
                    NavGraph(navController = navController)
                }
            },
        )
    }
}
