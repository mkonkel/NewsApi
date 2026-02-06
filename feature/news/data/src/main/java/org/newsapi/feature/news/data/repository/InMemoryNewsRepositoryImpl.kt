package org.newsapi.feature.news.data.repository

import org.newsapi.feature.news.data.local.dao.ArticleDao
import org.newsapi.feature.news.data.local.dao.CacheMetadataDao
import org.newsapi.feature.news.data.local.entity.CacheMetadataEntity
import org.newsapi.feature.news.data.remote.response.ArticleResponse
import java.time.LocalDate
import javax.inject.Inject

internal class InMemoryNewsRepositoryImpl @Inject constructor(
    private val articleDao: ArticleDao,
    private val cacheMetadataDao: CacheMetadataDao,
) : InMemoryNewsRepository {
    override suspend fun getArticles(): List<ArticleResponse> =
        articleDao.getAll().map { it.toResponse() }

    override suspend fun saveArticles(articles: List<ArticleResponse>, fetchDate: LocalDate) {
        articleDao.replaceAll(articles.map { it.toEntity() })
        cacheMetadataDao.insert(CacheMetadataEntity(fetchDate = fetchDate))
    }

    override suspend fun getLastFetchDate(): LocalDate? = cacheMetadataDao.getLastFetchDate()
}
