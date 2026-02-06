package org.newsapi.feature.news.data.datasource

import org.newsapi.feature.news.data.remote.response.ArticleResponse

internal interface NewsDataSource {
    suspend fun getArticles(forceRefresh: Boolean = false): List<ArticleResponse>
}
