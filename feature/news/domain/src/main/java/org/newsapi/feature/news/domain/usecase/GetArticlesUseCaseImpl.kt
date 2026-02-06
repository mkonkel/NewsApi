package org.newsapi.feature.news.domain.usecase

import org.newsapi.feature.news.domain.repository.NewsRepository
import org.newsapi.feature.news.presentation.domain.GetArticlesUseCase
import org.newsapi.feature.news.presentation.domain.model.ArticleListItem

internal class GetArticlesUseCaseImpl(
    private val repository: NewsRepository,
) : GetArticlesUseCase {
    override suspend fun invoke(forceRefresh: Boolean): List<ArticleListItem> =
        repository.getArticles(forceRefresh).map {
            ArticleListItem(
                title = it.title,
                description = it.description,
                imageUrl = it.imageUrl,
                url = it.url,
                source = it.source.name,
                author = it.author
            )
        }
}
