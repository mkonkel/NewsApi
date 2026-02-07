package org.newsapi.feature.news.presentation.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.AsyncImage
import org.newsapi.core.ui.theme.Dimens
import org.newsapi.core.ui.theme.NewsApiTheme
import org.newsapi.feature.news.presentation.R
import org.newsapi.feature.news.presentation.dimens.NewsDimens
import org.newsapi.feature.news.presentation.domain.model.Article

@Composable
fun ArticleDetailContent(
    article: Article,
    onArticleUrlClick: (String) -> Unit,
    onScroll: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val scrollState = rememberScrollState()

    LaunchedEffect(scrollState) {
        snapshotFlow { scrollState.value }
            .collect { onScroll(it) }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(vertical = Dimens.PaddingMedium)
            .padding(bottom = NewsDimens.FooterPaddingBottom),
    ) {

        if (article.title != null) {
            Text(
                text = article.title,
                style = typography.headlineMedium,
            )
            Spacer(modifier = Modifier.height(Dimens.SpacingMedium))
        }

        if (article.imageUrl != null) {
            AsyncImage(
                model = article.imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .sizeIn(maxHeight = NewsDimens.DetailImageMaxHeight),
            )
            Spacer(modifier = Modifier.height(Dimens.SpacingMedium))
        }

        if (!article.content.isNullOrBlank()) {
            Text(
                text = article.content,
                style = typography.bodyMedium,
            )
            Spacer(modifier = Modifier.height(Dimens.SpacingMedium))
        }

        Text(
            text = stringResource(id = R.string.read_full_article),
            style = typography.bodyMedium,
            color = colorScheme.secondary,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onArticleUrlClick(article.url) }
                .padding(vertical = Dimens.SpacingSmall),
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(Dimens.SpacingMedium))

        ArticleFooter(
            source = article.source,
            author = article.author,
            publishedAt = article.publishedAt,
        )
    }
}

@Composable
fun ArticleFooter(
    source: String?,
    author: String?,
    publishedAt: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        if (!source.isNullOrBlank()) {
            Text(
                text = source,
                style = typography.labelSmall,
                color = colorScheme.onSurfaceVariant,
            )
        }
        if (!author.isNullOrBlank()) {
            Text(
                text = author,
                style = typography.labelSmall,
                color = colorScheme.onSurfaceVariant,
            )
        }
        Text(
            text = publishedAt,
            style = typography.labelSmall,
            color = colorScheme.onSurfaceVariant,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ArticleDetailContentPreview() {
    NewsApiTheme {
        ArticleDetailContent(
            article =
                Article(
                    title = "Breaking News",
                    description = "Lorem ipsum dolor sit amet",
                    content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt.",
                    imageUrl = null,
                    source = "Reuters",
                    url = "https://example.com/article",
                    publishedAt = "2026-02-07",
                    author = "John Doe",
                ),
            onArticleUrlClick = {},
            onScroll = {},
        )
    }
}
