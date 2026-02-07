package org.newsapi.feature.news.presentation.detail

import org.newsapi.feature.news.presentation.domain.model.Article

sealed interface ArticleDetailState {
    data object Loading : ArticleDetailState

    data class Content(
        val article: Article,
        val showFab: Boolean = false,
    ) : ArticleDetailState

    data class Error(
        val message: String,
    ) : ArticleDetailState
}
