package org.newsapi.feature.news.presentation.list

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
import org.newsapi.feature.news.presentation.domain.GetArticlesUseCase
import org.newsapi.feature.news.presentation.domain.model.ArticleListItem

class NewsListViewModelTest {
    private lateinit var getArticles: GetArticlesUseCase
    private fun createArticle(index: Int) = ArticleListItem(
        title = "Test Article_$index",
        description = "Test Description_$index",
        imageUrl = "https://example.com/image_$index.jpg",
        source = "Test Source_$index",
        url = "https://example.com/article_$index",
        author = "Test Author_$index",
    )

    @Before
    fun setup() {
        getArticles = mock()
    }

    @Test
    fun `initial load success - emits Loading then Content with articles`() = runTest {
        val articles = (1..3).map { createArticle(it) }
        whenever { getArticles(false) } doReturn articles

        val viewModel = NewsListViewModel(getArticles)

        viewModel.state.test {
            awaitItem() shouldBe NewsListState.Loading
            awaitItem() shouldBe NewsListState.Content(articles)
        }
    }

    @Test
    fun `initial load empty - emits Loading then Empty`() = runTest {
        whenever { getArticles(false) } doReturn emptyList()

        val viewModel = NewsListViewModel(getArticles)

        viewModel.state.test {
            awaitItem() shouldBe NewsListState.Loading
            awaitItem() shouldBe NewsListState.Empty
        }
    }

    @Test
    fun `initial load error - emits Loading then Error`() = runTest {
        val exception = IllegalStateException("Network error")
        whenever { getArticles(false) } doThrow exception

        val viewModel = NewsListViewModel(getArticles)

        viewModel.state.test {
            awaitItem() shouldBe NewsListState.Loading
            awaitItem().shouldBeInstanceOf<NewsListState.Error>()
        }
    }

    @Test
    fun `refresh from Content - emits Content with isRefreshing then Content with new articles`() = runTest {
        val oldArticles = (1..3).map { createArticle(it) }
        val newArticles = (1..3).map { createArticle(it * 5) }
        whenever { getArticles(false) } doReturn oldArticles
        whenever { getArticles(true) } doReturn newArticles

        val viewModel = NewsListViewModel(getArticles)

        viewModel.state.test {
            awaitItem() shouldBe NewsListState.Loading
            awaitItem() shouldBe NewsListState.Content(oldArticles)

            viewModel.refresh()

            awaitItem() shouldBe NewsListState.Content(oldArticles, isRefreshing = true)
            awaitItem() shouldBe NewsListState.Content(newArticles)
        }
    }
}
