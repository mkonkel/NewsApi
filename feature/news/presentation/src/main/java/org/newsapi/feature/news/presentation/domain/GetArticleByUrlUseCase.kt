package org.newsapi.feature.news.presentation.domain

import org.newsapi.feature.news.presentation.domain.model.Article

fun interface GetArticleByUrlUseCase {
    suspend operator fun invoke(url: String): Article?
}
