package org.newsapi.feature.news.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import org.newsapi.feature.news.data.local.dao.ArticleDao
import org.newsapi.feature.news.data.local.entity.ArticleEntity

@Database(
    entities = [ArticleEntity::class],
    version = 1,
    exportSchema = false,
)
internal abstract class NewsDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
}
