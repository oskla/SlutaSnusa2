package com.antisnusbolaget.slutasnusa2.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

val AppLightColorScheme = lightColorScheme(
    background = yellow,
    onBackground = black,
    secondary = gray,
    onSecondary = lightYellow,
    tertiary = darkYellow,
)

@Composable
fun SlutaSnutaTheme(
    content: @Composable () -> Unit,
) {
    val AppColorScheme = AppLightColorScheme

    MaterialTheme(
        colorScheme = AppColorScheme,
        typography = AppTypography,
        shapes = AppShapes,
        content = content,
    )
}
