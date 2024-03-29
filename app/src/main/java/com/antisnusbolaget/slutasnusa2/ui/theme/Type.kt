package com.antisnusbolaget.slutasnusa2.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.antisnusbolaget.slutasnusa2.R

val MontserratFamily = FontFamily(
    Font(resId = R.font.montserrat_bold),
    Font(resId = R.font.montserrat_medium),
)

// Set of Material typography styles to start with

val AppTypography = Typography(
    bodyMedium = TextStyle(
        fontFamily = FontFamily(Font(resId = R.font.montserrat_medium)),
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
    ),
    titleSmall = TextStyle(
        fontFamily = MontserratFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
    ),
    titleLarge = TextStyle(
        fontFamily = MontserratFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
    ),
    titleMedium = TextStyle(
        fontFamily = MontserratFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
    ),
)
