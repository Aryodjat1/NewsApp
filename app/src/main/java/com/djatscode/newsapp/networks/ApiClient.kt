package com.djatscode.newsapp.networks

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    companion object{

        val COUNTRY = "us"
        val API_KEY = "7af7e01be48548518fc7b59d5d5ce666"
        fun getClient() : Retrofit {
            val BASE_URL = "https://newsapi.org/v2/"
            val retrofit : Retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit
        }
    }
}