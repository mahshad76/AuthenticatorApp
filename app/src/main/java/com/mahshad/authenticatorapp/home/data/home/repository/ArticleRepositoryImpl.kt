package com.mahshad.authenticatorapp.home.data.home.repository

import android.util.Log
import com.mahshad.authenticatorapp.di.ComputationScheduler
import com.mahshad.authenticatorapp.di.IoScheduler
import com.mahshad.authenticatorapp.di.MainScheduler
import com.mahshad.authenticatorapp.home.data.home.database.ArticleDatabase
import com.mahshad.authenticatorapp.home.data.home.database.LikedArticleEntity
import com.mahshad.authenticatorapp.home.data.home.model.remote.toArticle
import com.mahshad.authenticatorapp.home.data.home.model.repository.Article
import com.mahshad.authenticatorapp.home.data.home.model.repository.toArticleEntity
import com.mahshad.authenticatorapp.home.network.home.ApiService
import io.reactivex.Flowable
import io.reactivex.Scheduler
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class ArticleRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val articleDatabase: ArticleDatabase,
    @ComputationScheduler private val computationScheduler: Scheduler,
    @IoScheduler private val ioScheduler: Scheduler,
    @MainScheduler private val mainScheduler: Scheduler
) : ArticleRepository {
    override fun getArticles(): Flowable<List<Article>> {
        val remoteArticleObservable = apiService
            .getRecentArticles("apple")
            .map { response ->
                if (response.isSuccessful) {
                    response.body()?.articles?.map { articleDTO ->
                        articleDTO.toArticle()
                    } ?: emptyList<Article>()
                } else {
                    emptyList<Article>()
                }
            }.toFlowable()
            .subscribeOn(ioScheduler)

        return Flowable.combineLatest(
            remoteArticleObservable,
            articleDatabase.Dao().getAllUsers()
        ) { remoteArticles: List<Article>, localArticles: List<LikedArticleEntity> ->
            remoteArticles.map { article ->
                if ((article.title) in (localArticles.map { it.title })) {
                    article.copy(isLiked = true)
                } else {
                    article.copy(isLiked = false)
                }
            }
        }.subscribeOn(computationScheduler)
    }

    override fun updateLikedArticles(article: Article) {
        val articleEntity = article.toArticleEntity()
        val dao = articleDatabase.Dao()

        val databaseOperation = if (article.isLiked) {
            dao.delete(articleEntity)
                .doOnComplete {
                    Log.d(
                        "ArticleAction",
                        "Successfully deleted '${article.title}' from liked articles."
                    )
                }
                .doOnError { error ->
                    Log.e(
                        "ArticleAction",
                        "Failed to delete '${article.title}': ${error.message}"
                    )
                }
        } else {
            dao.insert(articleEntity)
                .doOnComplete {
                    Log.d(
                        "ArticleAction",
                        "Successfully inserted '${article.title}' to liked articles."
                    )
                }
                .doOnError { error ->
                    Log.e(
                        "ArticleAction",
                        "Failed to insert '${article.title}': ${error.message}"
                    )
                }
        }

        databaseOperation
            .subscribeOn(ioScheduler)
            .subscribe()
    }
}