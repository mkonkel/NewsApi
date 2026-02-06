package org.newsapi.feature.news.data.repository

import org.newsapi.feature.news.data.local.dao.ArticleDao
import org.newsapi.feature.news.data.remote.response.ArticleResponse
import java.time.LocalDate
import javax.inject.Inject

internal class InMemoryNewsRepositoryImpl @Inject constructor(
    private val articleDao: ArticleDao,
) : InMemoryNewsRepository {
    override suspend fun getArticles(): List<ArticleResponse> =
        articleDao.getAll().map { it.toResponse() }

    override suspend fun saveArticles(articles: List<ArticleResponse>, fetchDate: LocalDate) {
        articleDao.replaceAll(articles.map { it.toEntity(fetchDate.toString()) })
    }

    override suspend fun getLastFetchDate(): LocalDate? =
        articleDao.getLastFetchDate()?.let { LocalDate.parse(it) }
}
