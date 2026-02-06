package org.newsapi.feature.news.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.newsapi.feature.news.data.remote.service.NewsApiService
import org.newsapi.feature.news.data.repository.HttpNewsRepository
import org.newsapi.feature.news.data.repository.HttpNewsRepositoryImpl
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DataNetworkModule {
    @Provides
    @Singleton
    fun provideNewsApiService(retrofit: Retrofit): NewsApiService = retrofit.create(NewsApiService::class.java)

    @Provides
    @Singleton
    fun provideHttpNewsRepository(service: NewsApiService): HttpNewsRepository = HttpNewsRepositoryImpl(service)
}
