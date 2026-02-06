package org.newsapi.feature.news.data.repository

import org.newsapi.feature.news.data.remote.request.TopHeadlinesRequest
import org.newsapi.feature.news.data.remote.response.ArticleResponse
import javax.inject.Inject

internal class NewsDataSource
@Inject
constructor(
    private val httpRepository: HttpNewsRepositoryImpl,
    private val inMemoryRepository: InMemoryNewsRepositoryImpl,
) {
    suspend fun getTopHeadlines(
        category: String? = null,
        country: String? = null,
        sources: String? = null,
        query: String? = null,
        pageSize: Int = 20,
        page: Int = 1,
    ): List<ArticleResponse> {
        val request = TopHeadlinesRequest(
            category = category,
            country = country,
            sources = sources,
            query = query,
            pageSize = pageSize,
            page = page,
        )

        // TODO: Add decision logic (cache vs API)
        // TODO: Check network availability
        // TODO: Check cache freshness

        // Na razie: bezpośrednio do HTTP
        return httpRepository.getTopHeadlines(request)
    }
}
