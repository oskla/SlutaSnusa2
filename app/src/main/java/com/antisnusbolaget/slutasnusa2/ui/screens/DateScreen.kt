package com.antisnusbolaget.slutasnusa2.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.antisnusbolaget.slutasnusa2.navigation.HOME_ROUTE
import com.antisnusbolaget.slutasnusa2.navigation.ON_BOARDING_GRAPH_ROUTE
import com.antisnusbolaget.slutasnusa2.ui.components.Calendar
import com.antisnusbolaget.slutasnusa2.viewmodel.OnBoardingViewModel
import com.antisnusbolaget.slutasnusa2.viewmodel.`interface`.OnBoardingEvent
import org.koin.androidx.compose.koinViewModel

@Composable
fun DateScreen(viewModel: OnBoardingViewModel = koinViewModel(), navController: NavController) {
    DateScreenContent(
        onDateSelected = { dateInMillis ->
            viewModel.handleEvents(OnBoardingEvent.SetDate(dateInMillis))
        },
        navController,
    )
}

@Composable
fun DateScreenContent(
    onDateSelected: (Long) -> Unit,
    navController: NavController,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .clickable {
                navController.navigate(HOME_ROUTE) {
                    popUpTo(ON_BOARDING_GRAPH_ROUTE) {
                        inclusive = true
                    }
                }
            },
    ) {
        Calendar(onDateSelected = onDateSelected)
    }
}

private const val componentName = "DateScreen"

@Preview("$componentName (light)", showBackground = true)
@Preview("$componentName (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview("$componentName (big font)", fontScale = 1.5f, showBackground = true)
@Preview("$componentName (large screen)", device = Devices.PIXEL_C)
@Composable
private fun PreviewComponent() {
    // DateScreen()
}
