package org.newsapi.feature.news.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.newsapi.feature.news.data.datasource.NewsDataSource
import org.newsapi.feature.news.data.datasource.NewsDataSourceImpl
import org.newsapi.feature.news.data.date.DateProvider
import org.newsapi.feature.news.data.date.DateProviderImpl
import org.newsapi.feature.news.data.repository.HttpNewsRepositoryImpl
import org.newsapi.feature.news.data.repository.InMemoryNewsRepository
import org.newsapi.feature.news.data.repository.InMemoryNewsRepositoryImpl
import org.newsapi.feature.news.data.repository.NewsRepositoryImpl
import org.newsapi.feature.news.domain.repository.NewsRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DataModule {
    @Provides
    @Singleton
    fun provideDateProvider(): DateProvider = DateProviderImpl()

    @Provides
    @Singleton
    fun provideNewsDataSource(
        httpRepository: HttpNewsRepositoryImpl,
        inMemoryRepository: InMemoryNewsRepositoryImpl,
        dateProvider: DateProviderImpl,
    ): NewsDataSource =
        NewsDataSourceImpl(
            httpRepository = httpRepository,
            inMemoryRepository = inMemoryRepository,
            dateProvider = dateProvider,
        )

    @Provides
    @Singleton
    fun provideNewsRepository(
        newsDataSource: NewsDataSource,
        inMemoryNewsRepository: InMemoryNewsRepository
    ): NewsRepository = NewsRepositoryImpl(newsDataSource, inMemoryNewsRepository)
}
