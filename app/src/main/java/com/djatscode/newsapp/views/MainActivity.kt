package com.djatscode.newsapp.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.djatscode.newsapp.R
import com.djatscode.newsapp.models.headline.HeadlineModel
import com.djatscode.newsapp.networks.ApiClient
import com.djatscode.newsapp.networks.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val apiInterface : ApiInterface = ApiClient.getClient().create(ApiInterface::class.java)
        val call : Call<HeadlineModel> = apiInterface.getHeadlines("us", ApiClient.API_KEY)

            call.enqueue(object : Callback<HeadlineModel>{
                override fun onResponse(
                    call: Call<HeadlineModel>,
                    response: Response<HeadlineModel>
                ) {
                    val headline = response.body()?.articles
                    Log.d("Hit Headline", "Headline size ${headline?.size}")
                    // bind views
                }

                override fun onFailure(call: Call<HeadlineModel>, t: Throwable) {
                    Log.d("Hit Headline", "Gagal fetch news")
                }

            })

    }
}