package org.newsapi.feature.news.data.remote.service

import org.newsapi.feature.news.data.remote.response.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

internal interface NewsApiService {
    @GET("v2/top-headlines")
    suspend fun getTopHeadlines(
        @Query("category") category: String?,
        @Query("country") country: String?,
        @Query("sources") sources: String?,
        @Query("q") query: String?,
        @Query("pageSize") pageSize: Int,
        @Query("page") page: Int,
    ): Response<NewsResponse>
}
