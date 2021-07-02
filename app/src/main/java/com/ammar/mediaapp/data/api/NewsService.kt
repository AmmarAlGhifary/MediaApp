package com.ammar.mediaapp.data.api

import com.ammar.mediaapp.BuildConfig.API_KEY
import com.ammar.mediaapp.data.model.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("v2/top-headlines")
    suspend fun getBreakingNews(
        @Query("country")
        countryCode: String = "us",
        @Query("pageSize")
        pageSize : Int = 100,
        @Query("apiKey")
        apiKey: String = API_KEY
    ) : Response<NewsResponse>

    @GET("v2/everything")
    suspend fun searchForNews(
        @Query("q")
        searchQuery: String,
        @Query("apiKey")
        apiKey: String = API_KEY
    ): Response<NewsResponse>

    @GET("v2/top-headlines")
    suspend fun getCategoryTabNews(
        @Query("category")
        category : String,
        @Query("country")
        countryCode: String = "us",
        @Query("pageSize")
        pageSize : Int = 100,
        @Query("apiKey")
        apiKey: String = API_KEY
    ): Response<NewsResponse>

    @GET("v2/top-headlines")
    suspend fun getSourceTabNews(
        @Query("sources")
        sources: String,
        @Query("pageSize")
        pageSize : Int = 100,
        @Query("apiKey")
        apiKey: String = API_KEY
    ): Response<NewsResponse>
}