package org.newsapi.feature.news.data.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.newsapi.feature.news.data.local.NewsDatabase
import org.newsapi.feature.news.data.local.dao.ArticleDao
import org.newsapi.feature.news.data.local.dao.CacheMetadataDao
import org.newsapi.feature.news.data.repository.InMemoryNewsRepository
import org.newsapi.feature.news.data.repository.InMemoryNewsRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DataDatabaseModule {
    @Provides
    @Singleton
    fun provideNewsDatabase(
        @ApplicationContext context: Context,
    ): NewsDatabase =
        Room
            .databaseBuilder(
                context,
                NewsDatabase::class.java,
                "news_database",
            ).fallbackToDestructiveMigration(dropAllTables = true)
            .build()

    @Provides
    @Singleton
    fun provideArticleDao(database: NewsDatabase): ArticleDao = database.articleDao()

    @Provides
    @Singleton
    fun provideCacheMetadataDao(database: NewsDatabase): CacheMetadataDao = database.cacheMetadataDao()

    @Provides
    @Singleton
    fun provideInMemoryNewsRepository(
        articleDao: ArticleDao,
        cacheMetadataDao: CacheMetadataDao,
    ): InMemoryNewsRepository = InMemoryNewsRepositoryImpl(articleDao, cacheMetadataDao)
}
