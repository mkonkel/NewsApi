package org.newsapi.feature.news.data.repository

import org.newsapi.feature.news.data.datasource.NewsDataSource
import org.newsapi.feature.news.data.mapper.toArticle
import org.newsapi.feature.news.domain.model.Article
import org.newsapi.feature.news.domain.repository.NewsRepository

internal class NewsRepositoryImpl(
    private val newsDataSource: NewsDataSource,
) : NewsRepository {
    override suspend fun getArticles(): List<Article> = newsDataSource.getArticles().map { it.toArticle() }
}
