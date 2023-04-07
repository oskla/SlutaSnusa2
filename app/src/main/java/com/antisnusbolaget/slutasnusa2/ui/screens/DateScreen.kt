package com.antisnusbolaget.slutasnusa2.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.antisnusbolaget.slutasnusa2.ui.components.Calendar
import com.antisnusbolaget.slutasnusa2.viewmodel.OnBoardingViewModel
import com.antisnusbolaget.slutasnusa2.viewmodel.`interface`.Event
import org.koin.androidx.compose.koinViewModel

@Composable
fun DateScreen(viewModel: OnBoardingViewModel = koinViewModel()) {
    DateScreenContent(
        onAction = { viewModel.handleAction(it) },
    )
}

@Composable
fun DateScreenContent(
    onAction: (Event) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray),
    ) {
        Calendar(onAction = onAction)
    }
}

private const val componentName = "DateScreen"

@Preview("$componentName (light)", showBackground = true)
@Preview("$componentName (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview("$componentName (big font)", fontScale = 1.5f, showBackground = true)
@Preview("$componentName (large screen)", device = Devices.PIXEL_C)
@Composable
private fun PreviewComponent() {
    DateScreen()
}
