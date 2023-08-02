package com.antisnusbolaget.slutasnusa2.ui.screens.onboardingscreen

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.antisnusbolaget.slutasnusa2.ui.components.TextBold
import com.antisnusbolaget.slutasnusa2.ui.theme.black
import com.antisnusbolaget.slutasnusa2.ui.theme.orange
import kotlin.math.roundToInt

private val thumb_size = DpSize(32.dp, 32.dp)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CostScreen(
    onValueChangeFinished: (Int) -> Unit,
    sliderValue: Float,
) {
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
        val sliderPosition = remember { mutableStateOf(sliderValue) }

        // This makes sure that the slider position is updated on start.
        //  99% of the times this will be 0 from the start, but you know, edge cases and stuff.
        //  Remove if causing bug.
        LaunchedEffect(key1 = sliderValue) {
            sliderPosition.value = sliderValue
        }

        TextBold(
            text = "Vad kostar en dosa?",
            textAlign = TextAlign.Center,
        ) // TODO use string resources

        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize(),
        ) {
            TextBold(text = "${sliderPosition.value.roundToInt()} kr", fontSize = 100.sp)

            Slider(
                modifier = Modifier.padding(horizontal = 0.dp),
                value = sliderPosition.value,
                valueRange = 0f..100f,
                onValueChange = { sliderPosition.value = it },
                onValueChangeFinished = {
                    onValueChangeFinished(sliderPosition.value.roundToInt())
                },
                colors = SliderDefaults.colors(
                    activeTrackColor = black,
                    inactiveTrackColor = orange,
                    thumbColor = black,
                ),
                thumb = {
                    SliderDefaults.Thumb(
                        interactionSource = MutableInteractionSource(),
                        thumbSize = thumb_size,
                    )
                },
            )
        }
    }
}

private const val componentName = "Cost Screen"

@Preview("$componentName (light)", showBackground = true)
@Preview("$componentName (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview("$componentName (big font)", fontScale = 1.5f, showBackground = true)
@Preview("$componentName (large screen)", device = Devices.PIXEL_C)
@Composable
private fun PreviewComponent() {
    CostScreen(onValueChangeFinished = {}, sliderValue = 43f)
}
