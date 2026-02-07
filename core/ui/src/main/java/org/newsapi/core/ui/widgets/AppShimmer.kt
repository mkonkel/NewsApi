package org.newsapi.core.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.ShimmerBounds
import com.valentinilk.shimmer.rememberShimmer
import com.valentinilk.shimmer.shimmer
import org.newsapi.core.ui.theme.ExtendedTheme
import org.newsapi.core.ui.theme.NewsApiTheme

@Composable
fun ShimmerBox(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit,
) {
    val shimmer = rememberShimmer(shimmerBounds = ShimmerBounds.Window)
    Box(
        modifier = modifier.shimmer(shimmer),
        content = content,
    )
}

@Composable
fun ShimmerPlaceholder(
    modifier: Modifier = Modifier,
    color: Color = ExtendedTheme.colors.shimmerBase,
) {
    Spacer(modifier = modifier.background(color))
}

@Preview(showBackground = true)
@Composable
private fun ShimmerBoxPreview() {
    NewsApiTheme {
        ShimmerBox {
            ShimmerPlaceholder(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
            )
        }
    }
}
