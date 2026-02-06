package org.newsapi.feature.news.data.datasource

import org.newsapi.feature.news.data.date.DateProvider
import org.newsapi.feature.news.data.remote.request.TopHeadlinesRequest
import org.newsapi.feature.news.data.remote.response.ArticleResponse
import org.newsapi.feature.news.data.repository.HttpNewsRepository
import org.newsapi.feature.news.data.repository.InMemoryNewsRepository
import javax.inject.Inject

internal class NewsDataSourceImpl
    @Inject
    constructor(
        private val httpRepository: HttpNewsRepository,
        private val inMemoryRepository: InMemoryNewsRepository,
        private val dateProvider: DateProvider,
    ) : NewsDataSource {
        override suspend fun getArticles(): List<ArticleResponse> {
            val today = dateProvider.get()
            val lastFetchDate = inMemoryRepository.getLastFetchDate()

            if (lastFetchDate == today) {
                return inMemoryRepository.getArticles()
            }

            val request = TopHeadlinesRequest()
            val articles = httpRepository.getTopHeadlines(request)
            inMemoryRepository.saveArticles(articles, today)

            return articles
        }
    }
