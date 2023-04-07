package com.antisnusbolaget.slutasnusa2.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

val AppLightColorScheme = lightColorScheme(
    background = darkYellow,
    onBackground = black,
    secondary = gray,
    onSecondary = lightYellow,
    tertiary = yellow,
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
