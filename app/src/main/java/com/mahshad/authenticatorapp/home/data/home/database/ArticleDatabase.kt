package com.mahshad.authenticatorapp.home.data.home.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [LikedArticleEntity::class], version = 2)
abstract class ArticleDatabase : RoomDatabase() {
    abstract fun Dao(): ArticleDAO
}