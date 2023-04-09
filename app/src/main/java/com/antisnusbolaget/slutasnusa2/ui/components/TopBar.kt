package com.antisnusbolaget.slutasnusa2.ui.components

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.antisnusbolaget.slutasnusa2.R

@Composable
fun TopBar(
    showTopBar: Boolean,
) {
    if (showTopBar) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(MaterialTheme.colorScheme.background),
        ) {
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.top_scaffold_yellow),
                contentDescription = "top",
                contentScale = ContentScale.Inside,
            )
        }
    }
}

private const val componentName = "TopBar"

@SuppressLint("UnrememberedMutableState")
@Preview("$componentName (light)", showBackground = true)
@Preview("$componentName (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview("$componentName (big font)", fontScale = 1.5f, showBackground = true)
@Preview("$componentName (large screen)", device = Devices.PIXEL_C)
@Composable
private fun PreviewComponent() {
    TopBar(showTopBar = true)
}
