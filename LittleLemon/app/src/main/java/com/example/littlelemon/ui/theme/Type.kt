package com.example.littlelemon.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    displayLarge = TextStyle(
        fontSize = 26.sp,
        fontWeight = FontWeight.Bold,
        color = charcoal
    ),
    headlineLarge = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        color = charcoal
    ),
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp,
        color = green
    ),
    bodyMedium = TextStyle(
        fontWeight = FontWeight.Bold,
        color = green
    ),
    labelLarge = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold
    )
)

// Extension properties to support h1, h2, body1, body2, and button styles if needed
val Typography.h1: TextStyle get() = displayLarge
val Typography.h2: TextStyle get() = headlineLarge
val Typography.body1: TextStyle get() = bodyLarge
val Typography.body2: TextStyle get() = bodyMedium
val Typography.button: TextStyle get() = labelLarge
