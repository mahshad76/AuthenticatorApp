package com.mahshad.authenticatorapp.home.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mahshad.authenticatorapp.databinding.ArticleHomeLayoutBinding
import com.mahshad.authenticatorapp.home.data.home.model.repository.Article

class ArticleAdapter(private val articleList: List<Article>) :
    RecyclerView.Adapter<ArticleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding = ArticleHomeLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ArticleViewHolder(binding)
    }

    override fun getItemCount(): Int = articleList.size

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        Glide.with(holder.image.context)
            .load(articleList[position].urlToImage)
            .into(holder.image)
        holder.title.text = articleList[position].title
        holder.publishAt.text = articleList[position].publishedAt
        holder.author.text = articleList[position].author
    }
}

class ArticleViewHolder(article: ArticleHomeLayoutBinding) :
    RecyclerView.ViewHolder(article.root) {
    val image: ImageView = article.itemImage
    val title: TextView = article.itemTitle
    val author: TextView = article.itemAuthor
    val publishAt: TextView = article.itemPublishAt
}