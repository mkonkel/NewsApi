package org.newsapi.feature.news.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import org.newsapi.feature.news.data.local.converter.LocalDateConverter
import org.newsapi.feature.news.data.local.dao.ArticleDao
import org.newsapi.feature.news.data.local.dao.CacheMetadataDao
import org.newsapi.feature.news.data.local.entity.ArticleEntity
import org.newsapi.feature.news.data.local.entity.CacheMetadataEntity

@Database(
    entities = [ArticleEntity::class, CacheMetadataEntity::class],
    version = 2,
    exportSchema = false,
)
@TypeConverters(LocalDateConverter::class)
internal abstract class NewsDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
    abstract fun cacheMetadataDao(): CacheMetadataDao
}
