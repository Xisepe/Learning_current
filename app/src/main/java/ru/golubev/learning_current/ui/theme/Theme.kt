package ru.golubev.learning_current.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LoginColorPalette = darkColors(
    background = MidnightBlack,
    primary = White,
    onPrimary = Color.Black
)

@Composable
fun LoginTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = LoginColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}