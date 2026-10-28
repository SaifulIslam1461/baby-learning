package com.kids.learningvideo.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val PrimaryColor = Color(0xFFFF6F61) // Fun Coral
val SecondaryColor = Color(0xFF4ECDC4) // Mint Green
val BackgroundColor = Color(0xFFF7F9FC)
val SurfaceColor = Color(0xFFFFFFFF)

private val LightColorScheme = lightColorScheme(
    primary = PrimaryColor,
    secondary = SecondaryColor,
    background = BackgroundColor,
    surface = SurfaceColor
)

@Composable
fun KidsLearningVideoTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        content = content
    )
}
