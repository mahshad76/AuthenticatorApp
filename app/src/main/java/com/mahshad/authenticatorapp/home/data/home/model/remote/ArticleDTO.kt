package com.mahshad.authenticatorapp.home.data.home.model.remote

import com.google.gson.annotations.SerializedName
import com.mahshad.authenticatorapp.home.data.home.model.repository.Article

data class ArticleDTO(
    @SerializedName("author")
    val author: String?,
    @SerializedName("content")
    val content: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("publishedAt")
    val publishedAt: String?,
    @SerializedName("sourceDTO")
    val sourceDTO: SourceDTO?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("urlToImage")
    val urlToImage: String?
)

fun ArticleDTO.toArticle(): Article = Article(
    author = this.author,
    publishedAt = this.publishedAt,
    title = this.title,
    urlToImage = this.urlToImage
)