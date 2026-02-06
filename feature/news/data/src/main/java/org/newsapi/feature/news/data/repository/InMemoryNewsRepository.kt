package org.newsapi.feature.news.data.repository

import org.newsapi.feature.news.data.remote.response.ArticleResponse
import java.time.LocalDate

internal interface InMemoryNewsRepository {
    suspend fun getArticles(): List<ArticleResponse>

    suspend fun getArticleByUrl(url: String): ArticleResponse?

    suspend fun saveArticles(
        articles: List<ArticleResponse>,
        fetchDate: LocalDate,
    )

    suspend fun getLastFetchDate(): LocalDate?
}
