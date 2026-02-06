package org.newsapi.feature.news.data.repository

import org.newsapi.feature.news.data.remote.response.ArticleResponse

internal interface InMemoryNewsRepository {
    suspend fun getTopHeadlines(
        category: String? = null,
        country: String? = null,
        sources: String? = null,
        query: String? = null,
        pageSize: Int = 20,
        page: Int = 1,
    ): List<ArticleResponse>
}
