package com.mahshad.authenticatorapp.home.ui.home

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jakewharton.rxbinding3.view.clicks
import com.mahshad.authenticatorapp.R
import com.mahshad.authenticatorapp.databinding.ArticleHomeLayoutBinding
import com.mahshad.authenticatorapp.home.data.home.model.repository.Article
import io.reactivex.Observable

class ArticleAdapter(
    private val articleList: List<Article>,
    private val fragmentNotify: (() -> Article, () -> Observable<Unit>) -> Unit
) :
    RecyclerView.Adapter<ArticleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding = ArticleHomeLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        val articleViewHolder = ArticleViewHolder(binding)
        fragmentNotify({ articleList.get(articleViewHolder.id) }) { articleViewHolder.likeIcon.clicks() }
        return articleViewHolder
    }

    override fun getItemCount(): Int = articleList.size

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        Glide.with(holder.image.context)
            .load(articleList[position].urlToImage)
            .into(holder.image)
        holder.title.text = articleList[position].title
        holder.publishAt.text = articleList[position].publishedAt
        holder.author.text = articleList[position].author
        holder.id = position
        holder.likeIcon.imageTintList = ColorStateList.valueOf(
            ContextCompat.getColor(
                holder.itemView.context,
                if (articleList[position].isLiked) R.color.blue_500 else R.color.black
            )
        )
    }
}

class ArticleViewHolder(article: ArticleHomeLayoutBinding) : RecyclerView.ViewHolder(article.root) {
    var id: Int = -1
    val image: ImageView = article.itemImage
    val title: TextView = article.itemTitle
    val author: TextView = article.itemAuthor
    val publishAt: TextView = article.itemPublishAt
    val likeIcon: ImageView = article.likeIcon
}