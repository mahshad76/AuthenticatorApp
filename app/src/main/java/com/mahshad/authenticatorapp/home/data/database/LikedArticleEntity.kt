package com.mahshad.authenticatorapp.home.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mahshad.authenticatorapp.home.data.favorite.model.FavoriteArticle

@Entity(tableName = "liked_articles")
data class LikedArticleEntity(
    @PrimaryKey @ColumnInfo(name = "article_title") val title: String = " ",
    @ColumnInfo(name = "article_author") val author: String?,
    @ColumnInfo(name = "published_date") val publishedAt: String?,
    @ColumnInfo(name = "image_url") val urlToImage: String?
)

fun LikedArticleEntity.toFavoriteArticle() = FavoriteArticle(
    title = this.title,
    urlToImage = this.urlToImage,
)
