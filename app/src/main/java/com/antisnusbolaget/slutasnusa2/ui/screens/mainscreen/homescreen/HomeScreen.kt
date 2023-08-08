package com.antisnusbolaget.slutasnusa2.ui.screens.mainscreen.homescreen

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.antisnusbolaget.slutasnusa2.navigation.Screen
import com.antisnusbolaget.slutasnusa2.ui.components.TextBold
import com.antisnusbolaget.slutasnusa2.viewmodel.LoadingState
import com.antisnusbolaget.slutasnusa2.viewmodel.homescreen.HomeScreenViewModel
import com.antisnusbolaget.slutasnusa2.viewmodel.homescreen.HomeState
import org.koin.androidx.compose.koinViewModel
import timber.log.Timber

@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = koinViewModel(),
    navController: NavController,
) {
    val uiState = viewModel.uiState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        when (uiState.value.loadingState) {
            LoadingState.FAILED -> {
                LaunchedEffect(uiState.value) {
                    navController.navigate(Screen.OnBoarding.route)
                }
            }
            LoadingState.LOADING -> {
                CircularProgressIndicator()
            }
            LoadingState.SUCCESS -> {
                HomeScreenContent(uiState = uiState)
            }
        }
    }
}

@Composable
fun HomeScreenContent(uiState: State<HomeState>) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        TextBold(
            modifier = Modifier.clickable { Timber.d("Osk, ${uiState.value.userData.costPerUnit}") },
            text = "${uiState.value.userData.costPerUnit}",
        )
    }
}

private const val componentName = "Home Screen"

@Preview("$componentName (light)", showBackground = true)
@Preview("$componentName (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview("$componentName (big font)", fontScale = 1.5f, showBackground = true)
@Preview("$componentName (large screen)", device = Devices.PIXEL_C)
@Composable
private fun PreviewComponent() {
    HomeScreen(navController = rememberNavController())
}
