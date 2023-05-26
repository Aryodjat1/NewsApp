package com.djatscode.newsapp.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.djatscode.newsapp.databinding.ItemMainBinding
import com.djatscode.newsapp.models.news.Article

class NewsAdapter(private val listNews: List<Article>?) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    inner class NewsViewHolder(private val binding: ItemMainBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(newsItem: Article) {
            binding.apply {
                Glide.with(itemView)
                    .load(newsItem.urlToImage)
                    .centerCrop()
                    .into(imgNews)

                tvTitleNews.text = newsItem.title
                tvAuthorNews.text = newsItem.author
                tvDateNews.text = newsItem.publishedAt
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMainBinding.inflate(inflater, parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(listNews!![position])
    }

    override fun getItemCount(): Int {
        return listNews!!.size
    }
}