package org.newsapi.feature.news.presentation.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.newsapi.core.ui.theme.Dimens
import org.newsapi.core.ui.theme.ExtendedTheme
import org.newsapi.core.ui.theme.NewsApiTheme
import org.newsapi.core.ui.widgets.ShimmerBox
import org.newsapi.core.ui.widgets.ShimmerPlaceholder

@Composable
fun ArticleDetailShimmer(modifier: Modifier = Modifier) {
    ShimmerBox(
        modifier = modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(vertical = Dimens.PaddingMedium),
        ) {
            ShimmerPlaceholder(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(24.dp),
            )
            Spacer(modifier = Modifier.height(Dimens.SpacingMedium))

            ShimmerPlaceholder(
                modifier = Modifier.fillMaxWidth(),
                color = ExtendedTheme.colors.shimmerAccent,
            )
            Spacer(modifier = Modifier.height(Dimens.SpacingMedium))

            repeat(8) {
                ShimmerPlaceholder(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(14.dp),
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            ShimmerPlaceholder(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .height(14.dp),
                color = ExtendedTheme.colors.shimmerAccent,
            )
            Spacer(modifier = Modifier.height(Dimens.SpacingLarge))

            ShimmerPlaceholder(
                modifier = Modifier
                    .fillMaxWidth(0.3f)
                    .height(12.dp),
            )
            Spacer(modifier = Modifier.height(4.dp))
            ShimmerPlaceholder(
                modifier = Modifier
                    .fillMaxWidth(0.25f)
                    .height(12.dp),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ArticleDetailShimmerPreview() {
    NewsApiTheme {
        ArticleDetailShimmer()
    }
}
