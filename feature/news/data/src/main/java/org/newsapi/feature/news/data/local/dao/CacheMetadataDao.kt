package org.newsapi.feature.news.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import org.newsapi.feature.news.data.local.entity.CacheMetadataEntity
import java.time.LocalDate

@Dao
internal interface CacheMetadataDao {
    @Query("SELECT fetch_date FROM cache_metadata WHERE `key` = 'news'")
    suspend fun getLastFetchDate(): LocalDate?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(metadata: CacheMetadataEntity)
}
