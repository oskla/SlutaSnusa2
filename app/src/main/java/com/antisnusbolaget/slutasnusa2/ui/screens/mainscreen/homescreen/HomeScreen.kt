package com.antisnusbolaget.slutasnusa2.ui.screens.mainscreen.homescreen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.antisnusbolaget.slutasnusa2.navigation.Screen
import com.antisnusbolaget.slutasnusa2.ui.components.TextBold
import com.antisnusbolaget.slutasnusa2.viewmodel.HomeScreenLoadingState
import com.antisnusbolaget.slutasnusa2.viewmodel.HomeScreenViewModel
import org.koin.androidx.compose.koinViewModel

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
        when (uiState.value) {
            HomeScreenLoadingState.Failed -> {
                LaunchedEffect(uiState.value) {
                    navController.navigate(Screen.OnBoarding.route)
                }
            }
            HomeScreenLoadingState.Loading -> {
                CircularProgressIndicator()
            }
            HomeScreenLoadingState.Success -> {
                TextBold(text = "HAJ HAJ") // TODO HomeContent
            }
        }
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
