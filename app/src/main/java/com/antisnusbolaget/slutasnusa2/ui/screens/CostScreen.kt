package com.antisnusbolaget.slutasnusa2.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.antisnusbolaget.slutasnusa2.ui.components.TextBold
import com.antisnusbolaget.slutasnusa2.ui.theme.black
import com.antisnusbolaget.slutasnusa2.ui.theme.orange
import kotlin.math.roundToInt

@Composable
fun CostScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
    ) {
        val sliderPosition = remember { mutableStateOf(0f) }
        TextBold(text = "Vad kostar en dosa?", textAlign = TextAlign.Center)
        TextBold(text = "${sliderPosition.value.roundToInt()} kr", fontSize = 100.sp)
        Slider(
            modifier = Modifier.padding(horizontal = 20.dp),
            value = sliderPosition.value,
            valueRange = 0f..100f,
            onValueChange = { sliderPosition.value = it },
            colors = SliderDefaults.colors(
                activeTrackColor = black,
                inactiveTrackColor = orange,
                thumbColor = black,
            ),
        )
    }
}

private const val componentName = "Cost Screen"

@Preview("$componentName (light)", showBackground = true)
@Preview("$componentName (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview("$componentName (big font)", fontScale = 1.5f, showBackground = true)
@Preview("$componentName (large screen)", device = Devices.PIXEL_C)
@Composable
private fun PreviewComponent() {
    CostScreen()
}
