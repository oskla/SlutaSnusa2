package com.antisnusbolaget.slutasnusa2.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

val AppLightColorScheme = lightColorScheme(
    background = yellow,
    surface = yellow,
    onSurface = black,
    outline = black,
    outlineVariant = black,
    onPrimary = black,
    primary = black,
    onBackground = black,
    secondary = black,
    onSecondary = lightYellow,
    tertiary = darkYellow,
    onTertiary = black,
    onSurfaceVariant = black,
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
