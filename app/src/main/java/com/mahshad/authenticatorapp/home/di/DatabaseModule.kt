package com.mahshad.authenticatorapp.home.di

import android.content.Context
import androidx.room.Room
import com.mahshad.authenticatorapp.home.data.home.database.ArticleDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DatabaseModule {
    @Provides
    @Singleton
    fun provideArticleDatabase(context: Context): ArticleDatabase = Room.databaseBuilder(
        context,
        ArticleDatabase::class.java,
        "article_database"
    )
        .fallbackToDestructiveMigration(false)
        .build()
}