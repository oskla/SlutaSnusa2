package com.antisnusbolaget.slutasnusa2.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.antisnusbolaget.slutasnusa2.ui.theme.black
import com.antisnusbolaget.slutasnusa2.ui.theme.yellow

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

    // Handle color change onPress
    val interactionSource = remember { MutableInteractionSource() }.also {
        LaunchedEffect(it) {
            it.interactions.collect { interaction ->
                if (interaction is PressInteraction.Press) {
                    textColor.value = if (filled) black else yellow
                    buttonColor.value = if (filled) yellow else black
                }
                if (interaction is PressInteraction.Release) {
                    textColor.value = if (filled) yellow else black
                    buttonColor.value = if (filled) black else yellow
                }
            }
        }
    }

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .wrapContentHeight()
            .border(8.dp, black)
            .background(buttonColor.value)
            .fillMaxWidth(1f)
            .combinedClickable(
                interactionSource = interactionSource,
                indication = null,
            ) {
                onClick.invoke()
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
