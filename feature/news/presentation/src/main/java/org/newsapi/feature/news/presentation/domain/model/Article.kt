package org.newsapi.feature.news.presentation.domain.model

data class Article(
    val title: String?,
    val description: String?,
    val content: String?,
    val imageUrl: String?,
    val source: String?,
    val url: String,
    val publishedAt: String,
    val author: String?,
)
