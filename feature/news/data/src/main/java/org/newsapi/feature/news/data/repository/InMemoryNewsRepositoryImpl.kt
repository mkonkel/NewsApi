package org.newsapi.feature.news.data.repository

import kotlinx.coroutines.delay
import org.newsapi.feature.news.data.remote.response.ArticleResponse
import javax.inject.Inject

internal class InMemoryNewsRepositoryImpl @Inject constructor() : InMemoryNewsRepository {
    override suspend fun getTopHeadlines(
        category: String?,
        country: String?,
        sources: String?,
        query: String?,
        pageSize: Int,
        page: Int,
    ): Result<List<ArticleResponse>> {
        // TODO: Implement when storage layer is ready
        delay(100)
        return Result.failure(NotImplementedError("InMemory not implemented yet"))
    }
}
