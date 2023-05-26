package com.djatscode.newsapp.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.djatscode.newsapp.R
import com.djatscode.newsapp.databinding.ActivityMainBinding
import com.djatscode.newsapp.models.headline.HeadlineModel
import com.djatscode.newsapp.models.news.NewsModel
import com.djatscode.newsapp.networks.ApiClient
import com.djatscode.newsapp.networks.ApiInterface
import com.djatscode.newsapp.views.adapters.NewsAdapter
import kotlinx.android.synthetic.main.activity_main.img_headline
import kotlinx.android.synthetic.main.activity_main.recyclerView
import kotlinx.android.synthetic.main.activity_main.tv_author_headline
import kotlinx.android.synthetic.main.activity_main.tv_date_headline
import kotlinx.android.synthetic.main.activity_main.txt_title_headline
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private var binding : ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val apiInterface : ApiInterface = ApiClient.getClient().create(ApiInterface::class.java)

        //Headline call api
        apiInterface.getHeadlines("us", ApiClient.API_KEY).enqueue(object : Callback<HeadlineModel>{
                override fun onResponse(
                    call: Call<HeadlineModel>,
                    response: Response<HeadlineModel>
                ) {
                    val headline = response.body()?.articles
                    Log.d("Hit Headline", "Headline size ${headline?.size}")

                    val imageurl = response.body()?.articles?.get(0)?.urlToImage.toString()

                    // bind views
                    binding.apply {
                        Glide.with(this@MainActivity)
                            .load(imageurl)
                            .centerCrop()
                            .into(img_headline)

                        txt_title_headline.text = headline?.get(0)?.title
                        tv_author_headline.text = headline?.get(0)?.author
                        tv_date_headline.text = headline?.get(0)?.publishedAt
                    }

                }

                override fun onFailure(call: Call<HeadlineModel>, t: Throwable) {
                    Log.d("Hit Headline", "Gagal fetch news")
                }

            }
        )

        //NewsApi call
        apiInterface.getArticle("apple", ApiClient.API_KEY).enqueue(object : Callback<NewsModel>{
            override fun onResponse(call: Call<NewsModel>, response: Response<NewsModel>) {
                val news = response.body()?.articles

                Log.d("Hit News", "News size ${news}")

                binding.apply {
                    recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                    recyclerView.adapter = NewsAdapter(news)
                }
            }

            override fun onFailure(call: Call<NewsModel>, t: Throwable) {
                Log.d("Hit News", "Gagal Fetch")
            }

        }
        )

    }
}