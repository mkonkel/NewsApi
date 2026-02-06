package org.newsapi.feature.news.data.repository

import org.newsapi.core.network.exception.ErrorHandler
import org.newsapi.core.network.utils.getOrThrow
import org.newsapi.feature.news.data.remote.request.TopHeadlinesRequest
import org.newsapi.feature.news.data.remote.response.ArticleResponse
import org.newsapi.feature.news.data.remote.service.NewsApiService
import javax.inject.Inject

internal class HttpNewsRepositoryImpl
    @Inject
    constructor(
        private val service: NewsApiService,
    ) : HttpNewsRepository {
        override suspend fun getTopHeadlines(request: TopHeadlinesRequest): List<ArticleResponse> =
            runCatching {
                service
                    .getTopHeadlines(
                        category = request.category?.value,
                        country = request.country,
                        sources = request.sources,
                        query = request.query,
                        pageSize = request.pageSize,
                        page = request.page,
                    ).getOrThrow()
                    .articles ?: emptyList()
            }.getOrElse { error ->
                throw ErrorHandler.handle(error)
            }
    }
