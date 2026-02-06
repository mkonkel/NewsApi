package org.newsapi.feature.news.presentation.domain

import org.newsapi.feature.news.presentation.domain.model.ArticleListItem

fun interface GetArticlesUseCase {
    suspend operator fun invoke(forceRefresh: Boolean): List<ArticleListItem>
}
