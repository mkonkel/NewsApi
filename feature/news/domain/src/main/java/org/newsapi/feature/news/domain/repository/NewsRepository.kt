package org.newsapi.feature.news.domain.repository

import org.newsapi.feature.news.domain.model.Article

interface NewsRepository {
    suspend fun getArticles(): List<Article>
}
