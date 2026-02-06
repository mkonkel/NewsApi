package org.newsapi.feature.news.domain.model

data class Article(
    val title: String?,
    val description: String?,
    val content: String?,
    val imageUrl: String?,
    val source: Source,
    val url: String,
    val publishedAt: String,
    val author: String?,
)
