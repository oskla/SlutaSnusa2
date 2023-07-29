package com.antisnusbolaget.slutasnusa2.ui.screens.onboardingscreen

import android.content.res.Configuration
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.antisnusbolaget.slutasnusa2.ui.components.Calendar
import com.antisnusbolaget.slutasnusa2.ui.components.TextBold
import com.antisnusbolaget.slutasnusa2.ui.theme.black
import com.antisnusbolaget.slutasnusa2.ui.theme.yellow

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
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,
        ) {
            TextBold(
                text = "När slutade du snusa?", // TODO stringResources
                textAlign = TextAlign.Center,
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier,
            ) {
                RectangleButton(filled = true, buttonLabel = "Välj datum", onClick = openCalendar)

                Spacer(Modifier.height(16.dp))

                RectangleButton(filled = false, buttonLabel = "Idag", onClick = {
                    onDateSelected(System.currentTimeMillis())
                })
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RectangleButton(
    filled: Boolean,
    fontSize: TextUnit = 28.sp,
    buttonLabel: String,
    onClick: () -> Unit,
) {
    val buttonColor = remember { mutableStateOf(if (filled) black else yellow) }
    val textColor = remember { mutableStateOf(if (filled) yellow else black) }

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .wrapContentHeight()
            .border(8.dp, black)
            .background(buttonColor.value)
            .fillMaxWidth(1f)
            .combinedClickable(
                interactionSource = remember {
                    MutableInteractionSource()
                },
                indication = null,
            ) { }
            .pointerInput(Unit) {
                awaitEachGesture {
                    awaitFirstDown()
                    // TODO also do scale down
                    buttonColor.value = if (filled) yellow else black
                    textColor.value = if (filled) black else yellow
                    do {
                        val event = awaitPointerEvent()
                    } while (event.changes.any { it.pressed })
                    onClick.invoke()
                    textColor.value = if (filled) yellow else black
                    buttonColor.value = if (filled) black else yellow
                }
            }
            .padding(vertical = 32.dp, horizontal = 24.dp),
    ) {
        TextBold(
            modifier = Modifier,
            text = buttonLabel,
            textAlign = TextAlign.Center,
            fontSize = fontSize,
            color = textColor.value,
        )
        Icon(
            modifier = Modifier.size(42.dp),
            imageVector = Icons.Default.ArrowForward,
            contentDescription = "button",
            tint = textColor.value,
        )
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
