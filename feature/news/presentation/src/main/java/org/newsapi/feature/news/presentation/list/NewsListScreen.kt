package org.newsapi.feature.news.presentation.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import org.newsapi.core.ui.widgets.AppErrorState
import org.newsapi.core.ui.widgets.AppToolbar
import org.newsapi.feature.news.presentation.R

@Composable
fun NewsListScreen(
    viewModel: NewsListViewModel = hiltViewModel(),
    onArticleClick: (String) -> Unit = {},
) {
    val state by viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            AppToolbar(
                title = stringResource(id = R.string.app_name),
            )
        },
    ) { paddingValues ->
        Box(
            modifier =
            Modifier
                .fillMaxSize()
                .padding(paddingValues),
        ) {
            when (val currentState = state) {
                is NewsListState.Loading -> {
                    NewsListShimmer()
                }

                is NewsListState.Empty -> {
                    AppErrorState(
                        message = stringResource(id = R.string.empty_articles_message),
                        onRetry = { viewModel.refresh() },
                    )
                }

                is NewsListState.Content -> {
                    val pullToRefreshState = rememberPullToRefreshState()
                    PullToRefreshBox(
                        isRefreshing = currentState.isRefreshing,
                        onRefresh = { viewModel.refresh() },
                        state = pullToRefreshState,
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        NewsListContent(
                            articles = currentState.articles,
                            onArticleClick = onArticleClick,
                        )
                    }
                }

                is NewsListState.Error -> {
                    AppErrorState(
                        message = currentState.message,
                        onRetry = { viewModel.refresh() },
                    )
                }
            }
        }
    }
}
