package org.newsapi.feature.news.presentation.domain

import org.newsapi.feature.news.presentation.domain.model.ArticleListItem

interface GetArticlesUseCase {
    suspend fun invoke(forceRefresh: Boolean = false): List<ArticleListItem>
}
