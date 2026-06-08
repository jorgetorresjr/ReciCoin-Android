package com.example.recicoin.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Gray

val GreenPrimary = Color(0xFF065427)
val GreenSecondary = Color(0xFF39C16C)

val DarkText = Color(0xFF263640)

val BackgroundLight = Color(0xFFF5F5F5)
val SurfaceLight = Color(0xFFFFFFFF)

val White = Color(0xFFFFFFFF)

private val LightColors = lightColorScheme(

    primary = GreenPrimary,
    secondary = GreenSecondary,

    background = BackgroundLight,
    surface = SurfaceLight,

    onPrimary = White,
    onSecondary = White,

    onBackground = DarkText,
    onSurface = DarkText
)

private val DarkColors = darkColorScheme(

    primary = White,
    secondary = SurfaceLight,

    background = GreenPrimary,
    surface = Color(0xFF1E1E1E),

    onPrimary = Black,
    onSecondary = Gray,

    onBackground = White,
    onSurface = White
)

@Composable
fun ReciCoinTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    val colors = if (darkTheme) {
        DarkColors
    } else {
        LightColors
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}