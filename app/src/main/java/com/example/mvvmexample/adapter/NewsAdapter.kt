package com.example.mvvmexample.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.mvvmexample.R
import com.example.mvvmexample.model.Article
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.news_item.view.*


class NewsAdapter(var context: Context, private var articles: ArrayList<Article>) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.news_item, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.itemView.imageView.animation = AnimationUtils.loadAnimation(context,R.anim.fade_transition_animation)
        holder.itemView.constraintLayout.animation = AnimationUtils.loadAnimation(context,R.anim.fade_scale_animation)
        holder.tvName.text = articles[position].title
        holder.tvDesCription.text = articles[position].toString()
        Picasso.get().load(articles[position].urlToImage).into(holder.ivNews)
    }

    override fun getItemCount() = articles.size

    inner class NewsViewHolder(itemView: View) : ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById(R.id.tvName)
        var tvDesCription: TextView = itemView.findViewById(R.id.ttvDesCription)
        var ivNews: ImageView = itemView.findViewById(R.id.ivNews)
    }
}