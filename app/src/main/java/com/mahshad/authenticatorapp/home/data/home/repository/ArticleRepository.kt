package com.mahshad.authenticatorapp.home.data.home.repository

import com.mahshad.authenticatorapp.home.data.home.model.repository.Article
import io.reactivex.Single


interface ArticleRepository {
    fun getArticles(): Single<List<Article>>
}