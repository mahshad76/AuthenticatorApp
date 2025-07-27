package com.mahshad.authenticatorapp.home.data.favorite.repository

import com.mahshad.authenticatorapp.home.data.database.ArticleDatabase
import com.mahshad.authenticatorapp.home.data.database.toFavoriteArticle
import com.mahshad.authenticatorapp.home.data.favorite.model.FavoriteArticle
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoriteRepositoryImpl @Inject constructor(private val database: ArticleDatabase) :
    FavoriteRepository {
    override fun getFavoriteArticles(): Flowable<List<FavoriteArticle>> {
        return database.Dao().getAllArticles().map { articles ->
            articles.groupBy { it.publishedAt }
                .flatMap { (_, value) ->
                    value.sortedWith(
                        compareBy(
                            { it.title },
                            { it.author })
                    )
                        .map { it.toFavoriteArticle() }
                }
        }
    }
}