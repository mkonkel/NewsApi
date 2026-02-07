package org.newsapi.feature.news.data.repository

import org.newsapi.feature.news.data.datasource.NewsDataSource
import org.newsapi.feature.news.data.mapper.toArticle
import org.newsapi.feature.news.domain.model.Article
import org.newsapi.feature.news.domain.repository.NewsRepository

internal class NewsRepositoryImpl(
    private val newsDataSource: NewsDataSource,
    private val inMemoryNewsRepositoryImpl: InMemoryNewsRepository,
) : NewsRepository {
    override suspend fun getArticles(forceRefresh: Boolean): List<Article> = newsDataSource.getArticles(forceRefresh).map { it.toArticle() }

    override suspend fun getArticleByUrl(url: String): Article? = inMemoryNewsRepositoryImpl.getArticleByUrl(url)?.toArticle()
}
