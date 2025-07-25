package com.mahshad.authenticatorapp.home.data.home.database

import android.content.Context
import androidx.room.Room

object ArticleDatabaseProvider {
    @Volatile
    private var INSTANCE: ArticleDatabase? = null

    fun getDatabase(context: Context): ArticleDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                ArticleDatabase::class.java,
                "liked_articles_db"
            ).build()
            INSTANCE = instance
            instance
        }
    }
}