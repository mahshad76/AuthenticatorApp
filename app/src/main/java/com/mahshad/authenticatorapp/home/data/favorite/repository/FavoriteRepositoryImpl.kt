package com.mahshad.authenticatorapp.home.data.favorite.repository

import com.mahshad.authenticatorapp.di.IoScheduler
import com.mahshad.authenticatorapp.di.MainScheduler
import com.mahshad.authenticatorapp.home.data.database.ArticleDatabase
import com.mahshad.authenticatorapp.home.data.database.toFavoriteArticle
import com.mahshad.authenticatorapp.home.data.favorite.model.FavoriteArticle
import io.reactivex.Flowable
import io.reactivex.Scheduler
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoriteRepositoryImpl @Inject constructor(
    private val database: ArticleDatabase,
    @IoScheduler private val ioScheduler: Scheduler,
    @MainScheduler private val mainScheduler: Scheduler
) :
    FavoriteRepository {
    override fun getFavoriteArticles(): Flowable<List<FavoriteArticle>> {
        return database.Dao().getAllArticles()
            .observeOn(ioScheduler)
            .map { articles ->
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
            .observeOn(mainScheduler)
    }
}