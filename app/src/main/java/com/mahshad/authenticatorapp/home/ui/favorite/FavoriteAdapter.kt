package com.mahshad.authenticatorapp.home.ui.favorite

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mahshad.authenticatorapp.databinding.FavoriteArticleLayoutBinding
import com.mahshad.authenticatorapp.home.data.favorite.model.FavoriteArticle

class FavoriteAdapter(private val favoriteArticles: List<FavoriteArticle>) :
    RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}

class ViewHolder(favoriteArticleLayoutBinding: FavoriteArticleLayoutBinding) :
    RecyclerView.ViewHolder(favoriteArticleLayoutBinding.root) {

}