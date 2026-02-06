package org.newsapi.feature.news.data.repository

import org.newsapi.feature.news.data.local.entity.ArticleEntity
import org.newsapi.feature.news.data.remote.response.ArticleResponse
import org.newsapi.feature.news.data.remote.response.SourceResponse

internal fun ArticleResponse.toEntity(): ArticleEntity =
    ArticleEntity(
        url = url,
        sourceId = source.id,
        sourceName = source.name,
        author = author,
        title = title,
        description = description,
        urlToImage = urlToImage,
        publishedAt = publishedAt,
        content = content,
    )

internal fun ArticleEntity.toResponse(): ArticleResponse =
    ArticleResponse(
        source = SourceResponse(id = sourceId, name = sourceName),
        author = author,
        title = title,
        description = description,
        url = url,
        urlToImage = urlToImage,
        publishedAt = publishedAt,
        content = content,
    )
