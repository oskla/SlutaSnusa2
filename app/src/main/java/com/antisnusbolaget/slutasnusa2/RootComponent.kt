package com.antisnusbolaget.slutasnusa2

import androidx.activity.OnBackPressedCallback
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.antisnusbolaget.slutasnusa2.navigation.BottomBarVisibility
import com.antisnusbolaget.slutasnusa2.navigation.BottomNav
import com.antisnusbolaget.slutasnusa2.navigation.NavGraph
import com.antisnusbolaget.slutasnusa2.navigation.Screen
import com.antisnusbolaget.slutasnusa2.ui.components.TopBar

@Composable
fun RootComponent(moveToBackCallBack: OnBackPressedCallback) {
    val navController = rememberNavController()
    val bottomBarState = remember { mutableStateOf(BottomBarVisibility(isHomeScreen = false, isOnBoarding = false)) }
    val topBarState = rememberSaveable { mutableStateOf(false) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    bottomBarState.value = Screen.shouldShowBottomBar(navBackStackEntry?.destination?.route)
    topBarState.value = Screen.shouldShowTopBar(navBackStackEntry?.destination?.route)

    Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        Scaffold(
            content = { scaffoldPadding ->
                Box(modifier = Modifier.padding(paddingValues = scaffoldPadding)) {
                    NavGraph(navController = navController)
                }
            },
            bottomBar = {
                BottomNav(
                    bottomBarState = bottomBarState.value,
                    onClickNext = { Screen.nextScreen(navController) },
                    onClickBack = { Screen.onBackPressed(moveToBackCallBack, navController) },
                )
            },
            topBar = { TopBar(showTopBar = topBarState.value) },
        )
    }
}
