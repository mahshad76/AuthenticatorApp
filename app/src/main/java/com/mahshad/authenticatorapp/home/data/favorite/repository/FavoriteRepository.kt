package com.mahshad.authenticatorapp.home.data.favorite.repository

import com.mahshad.authenticatorapp.home.data.favorite.model.FavoriteArticle
import io.reactivex.Flowable

interface FavoriteRepository {
    fun getFavoriteArticles(): Flowable<List<FavoriteArticle>>
}