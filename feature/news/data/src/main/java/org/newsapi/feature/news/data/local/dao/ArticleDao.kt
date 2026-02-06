package org.newsapi.feature.news.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import org.newsapi.feature.news.data.local.entity.ArticleEntity

@Dao
internal interface ArticleDao {
    @Query("SELECT * FROM articles")
    suspend fun getAll(): List<ArticleEntity>

    @Query("SELECT * FROM articles WHERE url = :url LIMIT 1")
    suspend fun getByUrl(url: String): ArticleEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(articles: List<ArticleEntity>)

    @Query("DELETE FROM articles")
    suspend fun deleteAll()

    @Transaction
    suspend fun replaceAll(articles: List<ArticleEntity>) {
        deleteAll()
        insertAll(articles)
    }
}
