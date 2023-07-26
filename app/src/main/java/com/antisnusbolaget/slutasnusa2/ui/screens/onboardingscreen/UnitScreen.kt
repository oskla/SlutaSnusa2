package com.antisnusbolaget.slutasnusa2.ui.screens.onboardingscreen

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.antisnusbolaget.slutasnusa2.R
import com.antisnusbolaget.slutasnusa2.ui.components.TextBold
import com.antisnusbolaget.slutasnusa2.ui.theme.SlutaSnutaTheme

@Composable
fun UnitScreen(
    onClickSetUnit: (Int) -> Unit,
    amountOfUnitsLabel: String,
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
            text = "Hur många dosor snusar du i veckan?",
            textAlign = TextAlign.Center,
        )

        TextBold(text = amountOfUnitsLabel, fontSize = 100.sp)

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth(),
        ) {
            IconButton(
                onClick = { onClickSetUnit(-1) },
                modifier = Modifier.size(70.dp),
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_decrement),
                    contentDescription = "Subtract unit from total amount",
                )
            }
            Spacer(Modifier.width(40.dp))
            IconButton(
                onClick = { onClickSetUnit(+1) },
                modifier = Modifier.size(70.dp),
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_increment),
                    contentDescription = "Add unit to total amount",
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
        UnitScreen(onClickSetUnit = {}, amountOfUnitsLabel = "5")
    }
}
