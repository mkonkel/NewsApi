package org.newsapi.feature.news.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface Screen {
    @Serializable
    data object NewsList : Screen

    @Serializable
    data class ArticleDetail(val articleUrl: String) : Screen
}
