package com.antisnusbolaget.slutasnusa2

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
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

@Composable
fun RootComponent() {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val bottomBarState = remember { mutableStateOf(BooleanPair(shouldShowNav = false, shouldShowYellow = false)) }
    val topBarState = rememberSaveable { mutableStateOf(false) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    bottomBarState.value = Screen.shouldShowBottomBar(navBackStackEntry?.destination?.route)
    topBarState.value = Screen.shouldShowTopBar(navBackStackEntry?.destination?.route)

    Box(modifier = Modifier.fillMaxSize()) {
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
                    onClickNext = { /* TODO - a function that checks whats screen to go to next? If we don't find a clever and clean way to do this, maybe we have to rethink the bottomScaffold architecture. Maybe we can store that state in the viewmodel so that we know where we can tell the navcontroller where we want to go?  */ },
                )
            },
            topBar = { TopBar(topBarState = topBarState) },
        )
    }
}
