package org.newsapi.feature.news.data.repository

import org.newsapi.feature.news.data.remote.request.TopHeadlinesRequest
import org.newsapi.feature.news.data.remote.response.ArticleResponse

internal interface HttpNewsRepository {
    suspend fun getTopHeadlines(request: TopHeadlinesRequest): List<ArticleResponse>
}
