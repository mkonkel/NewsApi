package org.newsapi.core.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

internal val Yellow = Color(0xFFFFD700)
internal val Orange = Color(0xFFFF8C00)
internal val DarkGray = Color(0xFF424242)

@Immutable
data class ExtendedColorScheme(
    val shimmerBase: Color,
    val shimmerHighlight: Color,
    val accent: Color,
    val accentVariant: Color,
    val shimmerAccent: Color,
)

val LightExtendedColorScheme =
    ExtendedColorScheme(
        shimmerBase = Color(0xFFF5F5F5),
        shimmerHighlight = Color(0xFFFFFFE0),
        accent = Yellow,
        accentVariant = Orange,
        shimmerAccent = Yellow.copy(alpha = 0.3f),
    )

// TODO: Define dark extended color scheme
val DarkExtendedColorScheme = LightExtendedColorScheme

val LocalExtendedColorScheme =
    staticCompositionLocalOf {
        LightExtendedColorScheme
    }
