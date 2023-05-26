package com.djatscode.newsapp.models.headline


import com.google.gson.annotations.SerializedName

data class HeadlineModel(
    @SerializedName("articles")
    val articles: List<Article>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int
)