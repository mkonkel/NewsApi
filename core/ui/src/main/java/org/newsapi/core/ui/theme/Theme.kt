package org.newsapi.core.ui.theme

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import com.valentinilk.shimmer.LocalShimmerTheme
import com.valentinilk.shimmer.defaultShimmerTheme

private val LightColorScheme = lightColorScheme(
    primary = Yellow,
    secondary = Orange,
    background = Color.White,
    surface = Color.White,
    onBackground = Color.Black,
    onSurface = Color.Black,
    onSurfaceVariant = DarkGray,
)

// TODO: Define dark color scheme
private val DarkColorScheme = LightColorScheme

private val AppShimmerTheme =
    defaultShimmerTheme.copy(
        animationSpec =
            infiniteRepeatable(
                animation = tween(),
                repeatMode = RepeatMode.Restart,
            ),
    )

@Composable
fun NewsApiTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val extendedColorScheme = if (darkTheme) DarkExtendedColorScheme else LightExtendedColorScheme

    CompositionLocalProvider(
        LocalExtendedColorScheme provides extendedColorScheme,
        LocalShimmerTheme provides AppShimmerTheme,
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = AppTypography,
            shapes = AppShapes,
            content = content,
        )
    }
}

object ExtendedTheme {
    val colors: ExtendedColorScheme
        @Composable get() = LocalExtendedColorScheme.current
}
