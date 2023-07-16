package com.antisnusbolaget.slutasnusa2.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.antisnusbolaget.slutasnusa2.ui.screens.AchievementScreen
import com.antisnusbolaget.slutasnusa2.ui.screens.CostScreen
import com.antisnusbolaget.slutasnusa2.ui.screens.DateScreen
import com.antisnusbolaget.slutasnusa2.ui.screens.HomeScreen
import com.antisnusbolaget.slutasnusa2.ui.screens.SettingScreen
import com.antisnusbolaget.slutasnusa2.ui.screens.UnitScreen
import com.antisnusbolaget.slutasnusa2.viewmodel.OnBoardingViewModel
import org.koin.androidx.compose.koinViewModel

const val ON_BOARDING_GRAPH_ROUTE = "on_boarding_route"
const val HOME_ROUTE = "home_route"

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = ON_BOARDING_GRAPH_ROUTE,
    ) {
        navigation(
            startDestination = Screen.Cost.route,
            route = ON_BOARDING_GRAPH_ROUTE,
        ) {
            composable(Screen.Cost.route) {
                val viewModel = it.sharedViewModel<OnBoardingViewModel>(navController)
                CostScreen()
            }
            composable(Screen.Unit.route) {
                val viewModel = it.sharedViewModel<OnBoardingViewModel>(navController)
                UnitScreen()
            }
            composable(Screen.Date.route) {
                val viewModel = it.sharedViewModel<OnBoardingViewModel>(navController)
                DateScreen()
            }
        }

        navigation(
            startDestination = Screen.Home.route,
            route = HOME_ROUTE,
        ) {
            composable(Screen.Home.route) {
                HomeScreen()
            }
            composable(Screen.Settings.route) {
                SettingScreen()
            }
            composable(Screen.Achievement.route) {
                AchievementScreen()
            }
        }
    }
}

// Scope viewModel to graph, as soon as graph is popped -> viewModel with be cleared
@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(navController: NavController): T {
    val navGraphRoute = destination.parent?.route ?: return koinViewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return viewModel(parentEntry)
}
