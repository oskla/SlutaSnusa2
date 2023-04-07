package com.antisnusbolaget.slutasnusa2.ui.screens

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.antisnusbolaget.slutasnusa2.viewmodel.OnBoardingViewModel
import com.antisnusbolaget.slutasnusa2.viewmodel.`interface`.Event
import org.koin.androidx.compose.koinViewModel
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DateScreen(viewModel: OnBoardingViewModel = koinViewModel()) {
    val context = LocalContext.current

    DateScreenContent(
        context = context,
        onAction = { viewModel.handleAction(it) },
        text = viewModel.dateWhenQuit.value,
    )
}

@RequiresApi(Build.VERSION_CODES.O) // TODO Requires min sdk > 24
@Composable
fun DateScreenContent(
    context: Context,
    onAction: (Event) -> Unit,
    text: String,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray),
    ) {
        Text(text = "Välj datum din bög", fontSize = 25.sp)
        Calendar(context = context, onSelectedDate = onAction, text = text)
    }
}

@Composable
fun Calendar(context: Context, onSelectedDate: (Event) -> Unit, text: String) {
    // Initializing a Calendar
    val calendar = Calendar.getInstance()

    // Declaring integer values
    // for year, month and day
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    calendar.time = Date()

    // Declaring a string value to
    // store date in string format
    val mDate = remember { mutableStateOf("") }
    Column(
        modifier = Modifier.padding(16.dp),
    ) {}
}

private const val componentName = "DateScreen"

@RequiresApi(Build.VERSION_CODES.O)
@Preview("$componentName (light)", showBackground = true)
@Preview("$componentName (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview("$componentName (big font)", fontScale = 1.5f, showBackground = true)
@Preview("$componentName (large screen)", device = Devices.PIXEL_C)
@Composable
private fun PreviewComponent() {
    DateScreen()
}
