package org.newsapi.feature.news.presentation.domain.model

data class ArticleListItem(
    val title: String?,
    val description: String?,
    val imageUrl: String?,
    val url: String,
    val source: String?,
    val author: String?,
)
