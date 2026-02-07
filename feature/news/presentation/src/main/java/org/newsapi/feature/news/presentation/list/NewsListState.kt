package org.newsapi.feature.news.presentation.list

import org.newsapi.feature.news.presentation.domain.model.ArticleListItem

sealed interface NewsListState {
    data object Loading : NewsListState

    data object Empty : NewsListState

    data class Content(
        val articles: List<ArticleListItem>,
        val isRefreshing: Boolean = false,
    ) : NewsListState

    data class Error(val message: String) : NewsListState
}
