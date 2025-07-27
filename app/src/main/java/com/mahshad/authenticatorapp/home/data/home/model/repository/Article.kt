package com.mahshad.authenticatorapp.home.data.home.model.repository

import com.mahshad.authenticatorapp.home.data.database.LikedArticleEntity

data class Article(
    val author: String?,
    val publishedAt: String?,
    val title: String?,
    val urlToImage: String?,
    val isLiked: Boolean
)

fun Article.toArticleEntity() = LikedArticleEntity(
    author = this.author,
    publishedAt = this.publishedAt,
    title = this.title?:" ",
    urlToImage = this.urlToImage
)
