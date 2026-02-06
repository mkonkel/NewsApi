package org.newsapi.feature.news.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "cache_metadata")
internal data class CacheMetadataEntity(
    @PrimaryKey
    @ColumnInfo(name = "key")
    val key: String = "news",
    @ColumnInfo(name = "fetch_date")
    val fetchDate: LocalDate,
)
