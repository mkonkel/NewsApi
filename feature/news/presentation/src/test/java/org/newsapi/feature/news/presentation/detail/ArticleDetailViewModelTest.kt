package org.newsapi.feature.news.presentation.detail

import app.cash.turbine.test
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.doThrow
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.newsapi.feature.news.presentation.domain.GetArticleByUrlUseCase
import org.newsapi.feature.news.presentation.domain.model.Article

class ArticleDetailViewModelTest {
    private lateinit var getArticleByUrlUseCase: GetArticleByUrlUseCase
    private val articleUrl = "https://example.com/article"
    private val article = Article(
        title = "Test Article",
        description = "Test Description",
        content = "Test Content",
        imageUrl = "https://example.com/image.jpg",
        source = "Test Source",
        url = articleUrl,
        publishedAt = "2026-01-15T00:00:00Z",
        author = "Test Author",
    )

    @Before
    fun setup() {
        getArticleByUrlUseCase = mock()
    }

    @Test
    fun `article found - emits Loading then Content with article`() = runTest {
        whenever { getArticleByUrlUseCase(articleUrl) } doReturn article

        val testViewModel = ArticleDetailViewModel(articleUrl, getArticleByUrlUseCase)

        testViewModel.state.test {
            awaitItem() shouldBe ArticleDetailState.Loading
            awaitItem() shouldBe ArticleDetailState.Content(article)
        }
    }

    @Test
    fun `article not found - emits Loading then Error with not found message`() = runTest {
        whenever { getArticleByUrlUseCase(articleUrl) } doReturn null

        val testViewModel = ArticleDetailViewModel(articleUrl, getArticleByUrlUseCase)

        testViewModel.state.test {
            awaitItem() shouldBe ArticleDetailState.Loading
            awaitItem().shouldBeInstanceOf<ArticleDetailState.Error>()
        }
    }

    @Test
    fun `exception with message - emits Loading then Error with exception message`() = runTest {
        val exception = IllegalStateException("Test Exception")
        whenever { getArticleByUrlUseCase(articleUrl) } doThrow exception

        val testViewModel = ArticleDetailViewModel(articleUrl, getArticleByUrlUseCase)

        testViewModel.state.test {
            awaitItem() shouldBe ArticleDetailState.Loading
            with(awaitItem().shouldBeInstanceOf<ArticleDetailState.Error>()) {
                message shouldBe exception.message
            }
        }
    }
}
