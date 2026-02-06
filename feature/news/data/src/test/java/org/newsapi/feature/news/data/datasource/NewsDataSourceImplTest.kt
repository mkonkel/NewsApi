package org.newsapi.feature.news.data.datasource

import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import org.newsapi.feature.news.data.date.DateProvider
import org.newsapi.feature.news.data.remote.request.TopHeadlinesRequest
import org.newsapi.feature.news.data.remote.response.ArticleResponse
import org.newsapi.feature.news.data.remote.response.SourceResponse
import org.newsapi.feature.news.data.repository.HttpNewsRepository
import org.newsapi.feature.news.data.repository.InMemoryNewsRepository
import java.time.LocalDate

class NewsDataSourceImplTest {
    private val httpRepository: HttpNewsRepository = mock()
    private val inMemoryRepository: InMemoryNewsRepository = mock()
    private val dateProvider: DateProvider = mock()


    private val today = LocalDate.of(2025, 1, 15)
    private val yesterday = LocalDate.of(2025, 1, 14)

    private val cachedArticles =
        listOf(
            ArticleResponse(
                source = SourceResponse(id = "cached", name = "Cached Source"),
                url = "https://cached.com/article",
                publishedAt = "2025-01-15T00:00:00Z",
                title = "Cached Article",
            ),
        )

    private val httpArticles =
        listOf(
            ArticleResponse(
                source = SourceResponse(id = "http", name = "Http Source"),
                url = "https://http.com/article",
                publishedAt = "2025-01-15T12:00:00Z",
                title = "Http Article",
            ),
        )

    @Test
    fun `returns cached articles when cache is fresh`() =
        runTest {
            whenever { dateProvider.get() } doReturn today
            whenever { inMemoryRepository.getLastFetchDate() } doReturn today
            whenever { inMemoryRepository.getArticles() } doReturn cachedArticles

            val dataSource = NewsDataSourceImpl(
                httpRepository = httpRepository,
                inMemoryRepository = inMemoryRepository,
                dateProvider = dateProvider,
            )

            val result = dataSource.getArticles()

            assertEquals(cachedArticles, result)
            verify(httpRepository, never()).getTopHeadlines(TopHeadlinesRequest())
        }

    @Test
    fun `fetches from network when cache is stale`() =
        runTest {
            whenever { dateProvider.get() } doReturn today
            whenever { inMemoryRepository.getLastFetchDate() } doReturn yesterday
            whenever { httpRepository.getTopHeadlines(TopHeadlinesRequest()) } doReturn httpArticles

            val dataSource = NewsDataSourceImpl(
                httpRepository = httpRepository,
                inMemoryRepository = inMemoryRepository,
                dateProvider = dateProvider,
            )

            val result = dataSource.getArticles()

            assertEquals(httpArticles, result)
            verify(httpRepository).getTopHeadlines(TopHeadlinesRequest())
            verify(inMemoryRepository).saveArticles(httpArticles, today)
        }

    @Test
    fun `fetches from network when no cache exists`() =
        runTest {
            whenever { dateProvider.get() } doReturn today
            whenever { inMemoryRepository.getLastFetchDate() } doReturn null
            whenever { httpRepository.getTopHeadlines(TopHeadlinesRequest()) } doReturn httpArticles

            val dataSource = NewsDataSourceImpl(
                httpRepository = httpRepository,
                inMemoryRepository = inMemoryRepository,
                dateProvider = dateProvider,
            )

            val result = dataSource.getArticles()

            assertEquals(httpArticles, result)
            verify(httpRepository).getTopHeadlines(TopHeadlinesRequest())
            verify(inMemoryRepository).saveArticles(httpArticles, today)
        }

    @Test
    fun `saves articles with today date when fetching from network`() =
        runTest {
            whenever { dateProvider.get() } doReturn today
            whenever { inMemoryRepository.getLastFetchDate() } doReturn null
            whenever { httpRepository.getTopHeadlines(TopHeadlinesRequest()) } doReturn httpArticles

            val dataSource = NewsDataSourceImpl(
                httpRepository = httpRepository,
                inMemoryRepository = inMemoryRepository,
                dateProvider = dateProvider,
            )

            dataSource.getArticles()

            verify(inMemoryRepository).saveArticles(httpArticles, today)
        }

    @Test
    fun `does not save articles when cache is fresh`() =
        runTest {
            whenever { dateProvider.get() } doReturn today
            whenever { inMemoryRepository.getLastFetchDate() } doReturn today
            whenever { inMemoryRepository.getArticles() } doReturn cachedArticles

            val dataSource = NewsDataSourceImpl(
                httpRepository = httpRepository,
                inMemoryRepository = inMemoryRepository,
                dateProvider = dateProvider,
            )

            dataSource.getArticles()

            verify(inMemoryRepository, never()).saveArticles(any(), any())
        }
}
