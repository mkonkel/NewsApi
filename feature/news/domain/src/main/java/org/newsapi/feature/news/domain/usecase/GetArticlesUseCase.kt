package org.newsapi.feature.news.domain.usecase

import org.newsapi.feature.news.domain.model.Article
import org.newsapi.feature.news.domain.repository.NewsRepository

class GetArticlesUseCase(
    private val repository: NewsRepository,
) {
    suspend operator fun invoke(): List<Article> = repository.getArticles()
}
