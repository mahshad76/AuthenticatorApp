package com.mahshad.authenticatorapp.home.ui.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mahshad.authenticatorapp.databinding.FavoriteArticleLayoutBinding
import com.mahshad.authenticatorapp.home.data.favorite.model.FavoriteArticle

class FavoriteAdapter(private val favoriteArticles: List<FavoriteArticle>) :
    RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = FavoriteArticleLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = favoriteArticles.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = favoriteArticles[position].title
        Glide.with(holder.image.context)
            .load(favoriteArticles[position].urlToImage)
            .into(holder.image)
    }
}

class ViewHolder(favoriteArticleLayoutBinding: FavoriteArticleLayoutBinding) :
    RecyclerView.ViewHolder(favoriteArticleLayoutBinding.root) {
    val image: ImageView = favoriteArticleLayoutBinding.cardImage
    val title: TextView = favoriteArticleLayoutBinding.cardTitle
}