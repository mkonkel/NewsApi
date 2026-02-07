package org.newsapi.feature.news.data.mapper

import org.newsapi.feature.news.data.remote.response.ArticleResponse
import org.newsapi.feature.news.domain.model.Article
import org.newsapi.feature.news.domain.model.Source

internal fun ArticleResponse.toArticle(): Article = Article(
    title = title,
    description = description,
    content = content,
    imageUrl = urlToImage,
    source =
    Source(
        id = source.id,
        name = source.name,
    ),
    url = url,
    publishedAt = publishedAt,
    author = author,
)
