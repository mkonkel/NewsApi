package org.newsapi.feature.news.data.repository

import java.time.LocalDate

internal fun interface DateProvider {
    fun get(): LocalDate
}
