package com.mahshad.authenticatorapp.home.data.home.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface ArticleDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(article: LikedArticleEntity): Completable

    @Query("SELECT * FROM liked_articles")
    fun getAllUsers(): Single<List<LikedArticleEntity>>

    @Delete
    fun delete(article: LikedArticleEntity): Completable
}