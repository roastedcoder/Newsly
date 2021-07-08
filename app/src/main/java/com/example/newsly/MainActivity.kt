package com.example.newsly

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var adapter: NewsAdapter
    private var articles = mutableListOf<Article>()
    var pageNum = 1
    var totalResult = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        adapter = NewsAdapter(this@MainActivity, articles)
        rvNewsList.adapter = adapter
        val layoutManager = LinearLayoutManager(this@MainActivity)
        rvNewsList.layoutManager = layoutManager

        fabRefresh.setOnClickListener {
            pbProgress.visibility = View.VISIBLE
            getNews()
        }

        getNews()
        pbProgress.visibility = View.VISIBLE
    }

    private fun getNews() {
        Log.d("NEWSLY", "Request sent for $pageNum")

        val news = NewsService.newsInstance.getHeadLines("in", pageNum)
        news.enqueue(object : Callback<News> {
            override fun onResponse(call: Call<News>, response: Response<News>) {
                val news = response.body()
                if (news != null) {
                    Log.d("NEWSLY", news.toString())

                    totalResult = news.totalResults
                    articles.addAll(news.articles)
                    adapter.notifyDataSetChanged()
                    pbProgress.visibility = View.GONE
                }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                pbProgress.visibility = View.GONE
                Toast.makeText(
                    applicationContext,
                    "Something went wrong",
                    Toast.LENGTH_SHORT
                ).show()
                Log.d("NEWSLY", "Error in fetching news", t)
            }
        })
    }
}