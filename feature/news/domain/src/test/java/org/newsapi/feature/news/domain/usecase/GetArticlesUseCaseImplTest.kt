package org.newsapi.feature.news.domain.usecase

import io.kotest.matchers.shouldBe
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import org.newsapi.feature.news.domain.model.Article
import org.newsapi.feature.news.domain.model.Source
import org.newsapi.feature.news.domain.repository.NewsRepository

class GetArticlesUseCaseImplTest {
    private val repository: NewsRepository = mock()

    private val oldestArticle =
        Article(
            title = "Oldest",
            description = "Old desc",
            content = null,
            imageUrl = null,
            source = Source(id = "1", name = "Source A"),
            url = "https://example.com/1",
            publishedAt = "2026-01-10T10:00:00Z",
            author = null,
        )

    private val newestArticle =
        Article(
            title = "Newest",
            description = "New desc",
            content = null,
            imageUrl = null,
            source = Source(id = "2", name = "Source B"),
            url = "https://example.com/2",
            publishedAt = "2026-01-15T10:00:00Z",
            author = null,
        )

    private val middleArticle =
        Article(
            title = "Middle",
            description = "Middle desc",
            content = null,
            imageUrl = null,
            source = Source(id = "3", name = "Source C"),
            url = "https://example.com/3",
            publishedAt = "2026-01-12T10:00:00Z",
            author = null,
        )

    private val articleWithAllFields =
        Article(
            title = "Full Article",
            description = "Full description",
            content = "Full content",
            imageUrl = "https://example.com/image.jpg",
            source = Source(id = "4", name = "Test Source"),
            url = "https://example.com/full",
            publishedAt = "2026-01-14T10:00:00Z",
            author = "Test Author",
        )

    @Test
    fun `sorts articles by publishedAt descending`() = runTest {
        whenever { repository.getArticles(false) } doReturn
            listOf(
                oldestArticle,
                newestArticle,
                middleArticle,
            )

        val useCase = GetArticlesUseCaseImpl(repository)
        val result = useCase.invoke(forceRefresh = false)

        result.size shouldBe 3
        result[0].title shouldBe "Newest"
        result[1].title shouldBe "Middle"
        result[2].title shouldBe "Oldest"
    }

    @Test
    fun `correctly maps Article to ArticleListItem`() = runTest {
        whenever { repository.getArticles(false) } doReturn listOf(articleWithAllFields)

        val useCase = GetArticlesUseCaseImpl(repository)
        val result = useCase.invoke(forceRefresh = false)

        result.size shouldBe 1
        with(result[0]) {
            title shouldBe "Full Article"
            description shouldBe "Full description"
            imageUrl shouldBe "https://example.com/image.jpg"
            url shouldBe "https://example.com/full"
            source shouldBe "Test Source"
            author shouldBe "Test Author"
        }
    }

    @Test
    fun `passes forceRefresh false to repository`() = runTest {
        whenever { repository.getArticles(false) } doReturn emptyList()

        val useCase = GetArticlesUseCaseImpl(repository)
        useCase.invoke(forceRefresh = false)

        verify(repository).getArticles(false)
    }

    @Test
    fun `passes forceRefresh true to repository`() = runTest {
        whenever { repository.getArticles(true) } doReturn emptyList()

        val useCase = GetArticlesUseCaseImpl(repository)
        useCase.invoke(forceRefresh = true)

        verify(repository).getArticles(true)
    }
}
