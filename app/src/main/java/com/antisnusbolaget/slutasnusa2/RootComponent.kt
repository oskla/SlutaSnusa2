package com.antisnusbolaget.slutasnusa2

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.antisnusbolaget.slutasnusa2.navigation.BooleanPair
import com.antisnusbolaget.slutasnusa2.navigation.NavGraph
import com.antisnusbolaget.slutasnusa2.navigation.Screen
import com.antisnusbolaget.slutasnusa2.ui.components.BottomNav
import com.antisnusbolaget.slutasnusa2.ui.components.TopBar

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RootComponent() {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val bottomBarState = remember { mutableStateOf(BooleanPair(shouldShowNav = false, shouldShowYellow = false)) }
    val topBarState = rememberSaveable { mutableStateOf(false) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val onBoardingScreensIndex = remember { mutableStateOf(0) }

    fun onBackPressed() {
        onBoardingScreensIndex.value = onBoardingScreensIndex.value - 1
    }

    bottomBarState.value = Screen.shouldShowBottomBar(navBackStackEntry?.destination?.route)
    topBarState.value = Screen.shouldShowTopBar(navBackStackEntry?.destination?.route)

    Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        Scaffold(
            scaffoldState = scaffoldState,
            content = { scaffoldPadding ->
                Box(modifier = Modifier.padding(paddingValues = scaffoldPadding)) {
                    NavGraph(navController = navController)
                }
            },
            bottomBar = {
                BottomNav(
                    navController = navController,
                    bottomBarState = bottomBarState,
                    onClickNext = { navController.navigate(Screen.nextScreen(onBoardingScreensIndex)) },
                    onClickBack = { onBackPressed() },
                )
            },
            topBar = { TopBar(topBarState = topBarState) },
        )
    }
}
