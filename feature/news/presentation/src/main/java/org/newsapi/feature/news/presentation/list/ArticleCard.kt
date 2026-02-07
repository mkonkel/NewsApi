package org.newsapi.feature.news.presentation.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.AsyncImage
import org.newsapi.core.ui.theme.Dimens
import org.newsapi.core.ui.theme.NewsApiTheme
import org.newsapi.feature.news.presentation.dimens.NewsDimens
import org.newsapi.feature.news.presentation.domain.model.ArticleListItem

@Composable
fun ArticleCard(
    article: ArticleListItem,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = Dimens.CardElevation),
        colors = CardDefaults.cardColors(containerColor = colorScheme.surface),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimens.PaddingMedium),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AsyncImage(
                model = article.imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .size(NewsDimens.CardImageSize)
                    .clip(CircleShape)
                    .background(colorScheme.secondary.copy(alpha = 0.5f)),
                contentScale = ContentScale.Crop,
            )

            Spacer(modifier = Modifier.width(Dimens.SpacingMedium))

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = Dimens.SpacingSmall),
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = article.title ?: "",
                    style = MaterialTheme.typography.headlineSmall,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )

                Spacer(modifier = Modifier.height(Dimens.SpacingSmall))

                Text(
                    text = article.description ?: "",
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                )

                Spacer(modifier = Modifier.height(Dimens.SpacingSmall))

                Text(
                    text = buildString {
                        if (!article.source.isNullOrBlank()) {
                            append(article.source)
                        }
                        if (!article.author.isNullOrBlank()) {
                            if (isNotEmpty()) append("/")
                            append(article.author)
                        }
                    },
                    style = MaterialTheme.typography.labelSmall,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = colorScheme.onSurface.copy(alpha = 0.7f),
                )
            }

            Row(
                Modifier.width(NewsDimens.IndicatorStripWidth),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    imageVector = Icons.Default.ChevronRight,
                    contentDescription = null,
                    tint = colorScheme.secondary,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ArticleCardPreview() {
    NewsApiTheme {
        ArticleCard(
            article =
            ArticleListItem(
                title = "Breaking News: Major Event",
                description = "A significant event has occurred that will impact many people across the country.",
                imageUrl = null,
                url = "https://example.com/article",
                source = "Reuters",
                author = "John Doe",
            ),
            onClick = {},
        )
    }
}
