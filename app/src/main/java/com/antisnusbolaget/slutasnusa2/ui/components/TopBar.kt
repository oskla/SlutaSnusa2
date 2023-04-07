package com.antisnusbolaget.slutasnusa2.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TopBar(
    topBarState: MutableState<Boolean>,
) {
    if (topBarState.value) {
        Box(modifier = Modifier.fillMaxWidth().height(60.dp).background(Color.Yellow)) {
            Text(text = "TopBar")
        }
    }
}
