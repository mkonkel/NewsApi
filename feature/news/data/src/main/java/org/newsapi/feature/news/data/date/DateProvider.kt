package org.newsapi.feature.news.data.date

import java.time.LocalDate

internal fun interface DateProvider {
    fun get(): LocalDate
}
