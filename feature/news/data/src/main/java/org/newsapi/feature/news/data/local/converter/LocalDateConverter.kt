package org.newsapi.feature.news.data.local.converter

import androidx.room.TypeConverter
import java.time.LocalDate

internal class LocalDateConverter {
    @TypeConverter
    fun fromLocalDate(date: LocalDate): String = date.toString()

    @TypeConverter
    fun toLocalDate(value: String): LocalDate = LocalDate.parse(value)
}
