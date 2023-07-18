package com.antisnusbolaget.slutasnusa2.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.unit.sp
import com.antisnusbolaget.slutasnusa2.ui.components.Calendar
import com.antisnusbolaget.slutasnusa2.ui.components.TextBold
import com.antisnusbolaget.slutasnusa2.viewmodel.OnBoardingViewModel
import com.antisnusbolaget.slutasnusa2.viewmodel.`interface`.OnBoardingEvent

@Composable
fun DateScreen(viewModel: OnBoardingViewModel) {
    DateScreenContent(
        onDateSelected = { dateInMillis ->
            viewModel.handleEvents(OnBoardingEvent.SetDate(dateInMillis))
        },
    )
}

@Composable
fun DateScreenContent(
    onDateSelected: (Long) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
    ) {
        TextBold(
            text = "Slutade du snusa idag?",
            textAlign = TextAlign.Center,
        )

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            TextBold(
                modifier = Modifier.clickable { println("Pick todays date") },
                text = "Ja",
                textAlign = TextAlign.Center,
                fontSize = 100.sp,
            )
            Spacer(Modifier.height(30.dp))
            TextBold(
                modifier = Modifier.clickable { println("Open calender") },
                text = "Nej",
                textAlign = TextAlign.Center,
                fontSize = 100.sp,
            )
        }

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
