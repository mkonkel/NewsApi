package org.newsapi.feature.news.presentation.detail

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.core.net.toUri
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import org.newsapi.core.ui.theme.Dimens
import org.newsapi.core.ui.theme.ExtendedTheme
import org.newsapi.core.ui.widgets.AppErrorState
import org.newsapi.core.ui.widgets.AppToolbar
import org.newsapi.feature.news.presentation.R
import org.newsapi.feature.news.presentation.dimens.NewsDimens

@Composable
fun ArticleDetailScreen(
    viewModel: ArticleDetailViewModel = hiltViewModel(),
    onBackClick: () -> Unit = {},
) {
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current

    val fallbackTitle = stringResource(id = R.string.app_name)
    val toolbarTitle = when (val currentState = state) {
        is ArticleDetailState.Content -> currentState.article.title ?: fallbackTitle
        else -> fallbackTitle
    }

    val contentState = state as? ArticleDetailState.Content

    Scaffold(
        topBar = {
            AppToolbar(
                title = toolbarTitle,
                showBackButton = true,
                onBackClick = onBackClick,
                actionIcon = if (contentState != null) Icons.Default.Share else null,
                onActionClick = {
                    contentState?.let { shareArticle(context, it.article.url) }
                },
            )
        },
        bottomBar = {
            if (contentState != null) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(NewsDimens.BottomBarHeight)
                        .background(ExtendedTheme.colors.accentVariant)
                        .clickable { openArticleInBrowser(context, contentState.article.url) }
                        .padding(vertical = Dimens.SpacingMedium),
                ) {
                    Text(
                        text = stringResource(id = R.string.read_full_article),
                        style = androidx.compose.material3.MaterialTheme.typography.labelLarge,
                        color = androidx.compose.material3.MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                    )
                }
            }
        },
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
        ) {
            when (val currentState = state) {
                is ArticleDetailState.Loading -> {
                    ArticleDetailShimmer()
                }

                is ArticleDetailState.Content -> {
                    ArticleDetailContent(
                        article = currentState.article,
                    )
                }

                is ArticleDetailState.Error -> {
                    AppErrorState(
                        message = currentState.message,
                        onRetry = {},
                    )
                }
            }
        }
    }
}

private fun shareArticle(
    context: Context,
    url: String,
) {
    val shareText = context.getString(R.string.share_article_text, url)
    val intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, shareText)
        type = "text/plain"
    }
    context.startActivity(Intent.createChooser(intent, null))
}

private fun openArticleInBrowser(
    context: Context,
    url: String,
) {
    val intent = Intent().apply {
        action = Intent.ACTION_VIEW
        data = url.toUri()
    }
    context.startActivity(intent)
}
