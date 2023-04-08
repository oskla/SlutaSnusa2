package com.antisnusbolaget.slutasnusa2.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.antisnusbolaget.slutasnusa2.ui.screens.CostScreen
import com.antisnusbolaget.slutasnusa2.ui.screens.DateScreen
import com.antisnusbolaget.slutasnusa2.ui.screens.HomeScreen
import com.antisnusbolaget.slutasnusa2.ui.screens.UnitScreen

const val ON_BOARDING_GRAPH_ROUTE = "onboarding_route"

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Cost.route, route = ON_BOARDING_GRAPH_ROUTE) {
        composable(Screen.Home.route) { HomeScreen() }
        composable(Screen.Cost.route) { CostScreen() }
        composable(Screen.Unit.route) { UnitScreen() }
        composable(Screen.Date.route) { DateScreen() }
    }
}
