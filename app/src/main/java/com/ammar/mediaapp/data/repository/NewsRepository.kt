package com.ammar.mediaapp.data.repository

import com.ammar.mediaapp.data.api.RetrofitService
import com.ammar.mediaapp.data.database.ArticleDb
import com.ammar.mediaapp.data.model.Article

class NewsRepository(
    val db: ArticleDb
) {
    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        RetrofitService.newsApi.getBreakingNews(countryCode, pageNumber)

    suspend fun searchNews(searchQuery: String, pageNumber: Int) =
        RetrofitService.newsApi.searchForNews(searchQuery, pageNumber.toString())

    suspend fun getCategoryTabNews(category: String) =
        RetrofitService.newsApi.getCategoryTabNews(category)

    suspend fun getSourceTabNews(sources: String) =
        RetrofitService.newsApi.getSourceTabNews(sources)

    suspend fun insert(article: Article) = db.getArticleDao().insert(article)

    fun getSavedNews() = db.getArticleDao().getAllArticles()

    suspend fun deleteArticle(article: Article) = db.getArticleDao().deleteArticle(article)
}