package com.mahshad.authenticatorapp.home.data.home.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "liked_articles")
data class LikedArticleEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "article_author") val author: String?,
    @ColumnInfo(name = "published_date") val publishedAt: String?,
    @ColumnInfo(name = "article_title") val title: String?,
    @ColumnInfo(name = "image_url") val urlToImage: String?
)
