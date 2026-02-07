package org.newsapi.feature.news.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.newsapi.feature.news.domain.repository.NewsRepository
import org.newsapi.feature.news.domain.usecase.GetArticleByUrlUseCaseImpl
import org.newsapi.feature.news.domain.usecase.GetArticlesUseCaseImpl
import org.newsapi.feature.news.presentation.domain.GetArticleByUrlUseCase
import org.newsapi.feature.news.presentation.domain.GetArticlesUseCase

@Module
@InstallIn(SingletonComponent::class)
internal object DomainModule {
    @Provides
    fun provideGetArticlesUseCase(repository: NewsRepository): GetArticlesUseCase = GetArticlesUseCaseImpl(repository)

    @Provides
    fun provideGetArticleByUrlUseCase(repository: NewsRepository): GetArticleByUrlUseCase = GetArticleByUrlUseCaseImpl(repository)
}
