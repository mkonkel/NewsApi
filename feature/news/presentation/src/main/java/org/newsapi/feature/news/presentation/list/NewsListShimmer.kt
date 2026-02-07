package org.newsapi.feature.news.presentation.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.newsapi.core.ui.theme.Dimens
import org.newsapi.core.ui.theme.ExtendedTheme
import org.newsapi.core.ui.theme.NewsApiTheme
import org.newsapi.core.ui.widgets.ShimmerBox
import org.newsapi.core.ui.widgets.ShimmerPlaceholder
import org.newsapi.feature.news.presentation.dimens.NewsDimens

@Composable
fun NewsListShimmer(modifier: Modifier = Modifier) {
    ShimmerBox {
        LazyColumn(modifier = modifier) {
            items(16) {
                ArticleCardShimmerItem()
            }
        }
    }
}

@Composable
private fun ArticleCardShimmerItem(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(Dimens.PaddingSmall),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        ShimmerPlaceholder(
            modifier = Modifier
                .size(NewsDimens.CardImageSize)
                .clip(CircleShape),
            color = ExtendedTheme.colors.shimmerAccent,
        )

        Spacer(modifier = Modifier.width(Dimens.SpacingMedium))

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(end = Dimens.SpacingSmall),
        ) {
            ShimmerPlaceholder(
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .height(20.dp),
            )
            Spacer(modifier = Modifier.height(Dimens.SpacingSmall))
            ShimmerPlaceholder(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(14.dp),
            )
            Spacer(modifier = Modifier.height(4.dp))
            ShimmerPlaceholder(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(14.dp),
            )
            Spacer(modifier = Modifier.height(Dimens.SpacingSmall))
            ShimmerPlaceholder(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .height(12.dp),
            )
        }

        ShimmerPlaceholder(
            modifier = Modifier
                .width(NewsDimens.IndicatorStripWidth)
                .height(NewsDimens.CardImageSize),
            color = ExtendedTheme.colors.shimmerAccent,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun NewsListShimmerPreview() {
    NewsApiTheme {
        NewsListShimmer()
    }
}
