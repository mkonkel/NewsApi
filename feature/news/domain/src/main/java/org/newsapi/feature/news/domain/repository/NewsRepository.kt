package org.newsapi.feature.news.domain.repository

import org.newsapi.feature.news.domain.model.Article

interface NewsRepository {
    suspend fun getArticles(forceRefresh: Boolean = false): List<Article>

    suspend fun getArticleByUrl(url: String): Article?
}
