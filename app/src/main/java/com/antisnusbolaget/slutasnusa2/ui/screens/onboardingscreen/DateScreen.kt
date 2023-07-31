package com.antisnusbolaget.slutasnusa2.ui.screens.onboardingscreen

import android.content.res.Configuration
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.antisnusbolaget.slutasnusa2.ui.components.Calendar
import com.antisnusbolaget.slutasnusa2.ui.components.RectangleButton
import com.antisnusbolaget.slutasnusa2.ui.components.TextBold

@Composable
fun DateScreen(
    openCalendar: () -> Unit,
    dismissCalendar: () -> Unit,
    onDateSelected: (Long) -> Unit,
    onBackPressed: () -> Unit,
    showCalendar: Boolean,
) {
    BackHandler(onBack = onBackPressed)

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(
                    horizontal = ON_BOARDING_HORIZONTAL_PADDING.dp,
                    vertical = ON_BOARDING_VERTICAL_PADDING.dp,
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TextBold(
                text = "När slutade du snusa?", // TODO stringResources
                textAlign = TextAlign.Center,
            )

            // This is for centering the button pair
            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize(),
            ) {
                // Button pair
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier,
                ) {
                    RectangleButton(
                        filled = true,
                        buttonLabel = "Välj datum",
                        onClick = openCalendar,
                    )

                    Spacer(Modifier.height(16.dp))

                    RectangleButton(filled = false, buttonLabel = "Idag", onClick = {
                        onDateSelected(System.currentTimeMillis())
                    })
                }
            }
        }
        if (showCalendar) {
            Calendar(
                onDismiss = dismissCalendar,
                onDateSelected = { onDateSelected(it) },
            )
        }
    }
}

private const val componentName = "DateScreen"

@Preview("$componentName (light)", showBackground = true)
@Preview("$componentName (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview("$componentName (big font)", fontScale = 1.5f, showBackground = true)
@Preview("$componentName (large screen)", device = Devices.PIXEL_C)
@Composable
private fun PreviewComponent() {
    DateScreen(
        openCalendar = {},
        dismissCalendar = {},
        onDateSelected = {},
        onBackPressed = {},
        showCalendar = false,
    )
}
