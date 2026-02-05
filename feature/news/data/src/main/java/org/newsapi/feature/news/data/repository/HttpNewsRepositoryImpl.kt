package org.newsapi.feature.news.data.repository

import org.newsapi.core.network.exception.ErrorHandler
import org.newsapi.core.network.exception.UnknownException
import org.newsapi.feature.news.data.remote.request.TopHeadlinesRequest
import org.newsapi.feature.news.data.remote.response.ArticleResponse
import org.newsapi.feature.news.data.remote.service.NewsApiService
import javax.inject.Inject

internal class HttpNewsRepositoryImpl @Inject constructor(
    private val service: NewsApiService
) : HttpNewsRepository {
    override suspend fun getTopHeadlines(request: TopHeadlinesRequest): Result<List<ArticleResponse>> =
        try {
            val response =
                service.getTopHeadlines(
                    category = request.category,
                    country = request.country,
                    sources = request.sources,
                    query = request.query,
                    pageSize = request.pageSize,
                    page = request.page,
                )
            if (response.isSuccessful && response.body() != null) {
                val articles = response.body()!!.articles ?: emptyList()
                Result.success(articles)
            } else {
                Result.failure(UnknownException("Empty response body"))
            }
        } catch (e: Exception) {
            Result.failure(ErrorHandler.handle(e))
        }
}
