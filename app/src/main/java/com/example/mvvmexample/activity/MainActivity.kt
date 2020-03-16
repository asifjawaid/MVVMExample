package com.example.mvvmexample.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmexample.R
import com.example.mvvmexample.adapter.NewsAdapter
import com.example.mvvmexample.model.Article
import com.example.mvvmexample.viewmodel.NewsViewModel


class MainActivity : AppCompatActivity() {

    var articleArrayList: ArrayList<Article> = ArrayList()
    var newsAdapter: NewsAdapter? = null
    var rvHeadline: RecyclerView? = null
    var newsViewModel: NewsViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvHeadline = findViewById(R.id.rvNews)

        newsViewModel = ViewModelProviders.of(this).get(NewsViewModel::class.java)
        newsViewModel!!.init()

        newsViewModel!!.getNewsRepository()!!.observe(this, Observer { newsResponse ->
            val newsArticles = newsResponse!!.articles
            articleArrayList.addAll(newsArticles)
            newsAdapter!!.notifyDataSetChanged()
        })
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        if (newsAdapter == null) {
            newsAdapter = NewsAdapter(this, articleArrayList)
            rvHeadline!!.layoutManager = LinearLayoutManager(this)
            rvHeadline!!.adapter = newsAdapter
            rvHeadline!!.itemAnimator = DefaultItemAnimator()
            rvHeadline!!.isNestedScrollingEnabled = true
        } else {
            newsAdapter!!.notifyDataSetChanged()
        }
    }
}

