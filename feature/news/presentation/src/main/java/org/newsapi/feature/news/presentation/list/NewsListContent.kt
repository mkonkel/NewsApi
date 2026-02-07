package org.newsapi.feature.news.presentation.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.newsapi.core.ui.theme.Dimens
import org.newsapi.core.ui.theme.NewsApiTheme
import org.newsapi.feature.news.presentation.domain.model.ArticleListItem

@Composable
fun NewsListContent(
    articles: List<ArticleListItem>,
    onArticleClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding =
            PaddingValues(
                vertical = Dimens.PaddingMedium,
            ),
        verticalArrangement = Arrangement.spacedBy(Dimens.SpacingMedium),
    ) {
        items(
            items = articles,
            key = { it.url },
        ) { article ->
            ArticleCard(
                article = article,
                onClick = { onArticleClick(article.url) },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun NewsListContentPreview() {
    NewsApiTheme {
        NewsListContent(
            articles =
                listOf(
                    ArticleListItem(
                        title = "Breaking News: Major Event",
                        description = "A significant event has occurred.",
                        imageUrl = null,
                        url = "https://example.com/1",
                        source = "Reuters",
                        author = "John Doe",
                    ),
                    ArticleListItem(
                        title = "Tech Update: New Release",
                        description = "A new software version has been released today.",
                        imageUrl = null,
                        url = "https://example.com/2",
                        source = "TechCrunch",
                        author = "Jane Smith",
                    ),
                ),
            onArticleClick = {},
        )
    }
}
