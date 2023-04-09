package com.antisnusbolaget.slutasnusa2.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.antisnusbolaget.slutasnusa2.ui.theme.MontserratFamily
import com.antisnusbolaget.slutasnusa2.ui.theme.SlutaSnutaTheme
import com.antisnusbolaget.slutasnusa2.viewmodel.OnBoardingViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun UnitScreen(viewModel: OnBoardingViewModel = koinViewModel()) {
    UnitScreenContent()
}

@Composable
fun UnitScreenContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
    ) {
        Text(
            text = "Hur många dosor snusar du i veckan?",
            fontSize = 40.sp,
            //fontFamily = MontserratFamily,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.width(320.dp),
        )

        Text(
            text = "0", // Placeholder
            fontSize = 100.sp,
            //fontFamily = MontserratFamily,
            fontWeight = FontWeight.Bold,
        )

        Row {
            IconButton(onClick = { println("Increment") }) {
                Text(
                    text = "-",
                    fontSize = 115.sp,
                    //fontFamily = MontserratFamily,
                    fontWeight = FontWeight.Bold,
                )
            }
            Spacer(Modifier.size(29.dp))
            IconButton(onClick = { println("Decrement") }) {
                Text(
                    text = "+",
                    fontSize = 115.sp,
                    //fontFamily = MontserratFamily,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}

private const val componentName = "UnitScreen"

@Preview("$componentName (light)", showBackground = true)
@Preview("$componentName (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview("$componentName (big font)", fontScale = 1.5f, showBackground = true)
@Preview("$componentName (large screen)", device = Devices.PIXEL_C)
@Composable
private fun PreviewComponent() {
    SlutaSnutaTheme {
        UnitScreenContent()
    }
}
