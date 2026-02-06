package org.newsapi.feature.news.data.repository

import org.newsapi.feature.news.data.remote.response.ArticleResponse

internal interface NewsDataSource {
    suspend fun getArticles(): List<ArticleResponse>
}
