package com.example.recicoin.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(

    primary = GreenPrimary,
    secondary = GreenSecondary,

    background = BackgroundLight,
    surface = White,

    onPrimary = White,
    onSecondary = White,

    onBackground = DarkText,
    onSurface = DarkText
)

private val DarkColors = darkColorScheme(

    primary = GreenSecondary,
    secondary = GreenPrimary,

    background = Color(0xFF121212),
    surface = Color(0xFF1E1E1E),

    onPrimary = White,
    onSecondary = White,

    onBackground = White,
    onSurface = White
)