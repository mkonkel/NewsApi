package org.newsapi.feature.news.domain.usecase

import org.newsapi.feature.news.domain.repository.NewsRepository
import org.newsapi.feature.news.presentation.domain.GetArticleByUrlUseCase
import org.newsapi.feature.news.presentation.domain.model.Article as PresentationArticle

/**
 * Use case implementing GetArticleByUrlInputPort.
 * Internal - presentation uses the port interface.
 */
internal class GetArticleByUrlUseCaseImpl(
    private val repository: NewsRepository,
) : GetArticleByUrlUseCase {
    override suspend fun invoke(url: String): PresentationArticle? =
        repository.getArticleByUrl(url)?.let {
            PresentationArticle(
                title = it.title,
                description = it.description,
                content = it.content,
                imageUrl = it.imageUrl,
                source = it.source.name,
                url = it.url,
                publishedAt = it.publishedAt,
                author = it.author
            )
        }
}
