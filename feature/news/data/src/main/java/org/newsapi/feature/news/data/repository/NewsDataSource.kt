package org.newsapi.feature.news.data.repository

import org.newsapi.feature.news.data.remote.request.TopHeadlinesRequest
import org.newsapi.feature.news.data.remote.response.ArticleResponse
import javax.inject.Inject

internal interface NewsDataSource {
    suspend fun getArticles(): List<ArticleResponse>
}
