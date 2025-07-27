package com.mahshad.authenticatorapp.home.data.home.repository

import com.mahshad.authenticatorapp.home.data.home.model.repository.Article
import io.reactivex.Flowable


interface ArticleRepository {
    fun getArticles(): Flowable<List<Article>>
    fun updateLikedArticles(article: Article)
}