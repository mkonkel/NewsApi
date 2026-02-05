package org.newsapi.feature.news.data.remote.request

data class TopHeadlinesRequest(
    val category: String? = "general",
    val country: String? = null,
    val sources: String? = null,
    val query: String? = null,
    val pageSize: Int = DEFAULT_PAGE_SIZE,
    val page: Int = DEFAULT_PAGE,
) {
    companion object {
        const val DEFAULT_PAGE_SIZE = 20
        const val DEFAULT_PAGE = 1
    }
}
