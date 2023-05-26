package com.djatscode.newsapp.networks

import com.djatscode.newsapp.models.headline.HeadlineModel
import com.djatscode.newsapp.models.news.NewsModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiInterface {

    @GET("top-headlines")
    fun getHeadlines(
        @Query("country") country: String?,
        @Query("apikey") apikey: String?
    ): Call<HeadlineModel>

    @GET("everything")
    fun getArticle(
        @Query("apikey") apikey: String?
    ): Call<NewsModel>
}