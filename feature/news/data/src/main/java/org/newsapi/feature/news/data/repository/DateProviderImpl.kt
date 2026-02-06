package org.newsapi.feature.news.data.repository

import java.time.LocalDate
import javax.inject.Inject

internal class DateProviderImpl @Inject constructor() : DateProvider {
    override fun get(): LocalDate = LocalDate.now()
}
