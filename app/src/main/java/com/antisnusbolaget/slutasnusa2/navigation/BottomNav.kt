package com.antisnusbolaget.slutasnusa2.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BottomNav() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
    ) {
        Text(text = "navBar")
    }
}
