package com.antisnusbolaget.slutasnusa2.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.antisnusbolaget.slutasnusa2.ui.theme.AppTypography

@Composable
fun TextBold(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onBackground,
    textAlign: TextAlign = TextAlign.Start,
    maxLines: Int = 3,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    fontSize: TextUnit = 40.sp,
) {
    Text(
        modifier = modifier,
        text = text,
        textAlign = textAlign,
        maxLines = maxLines,
        overflow = overflow,
        color = color,
        style = AppTypography.titleLarge.copy(fontSize = fontSize),
    )
}
