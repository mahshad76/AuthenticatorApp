package com.mahshad.authenticatorapp.home.data.home.repository

import android.util.Log
import com.mahshad.authenticatorapp.di.ComputationScheduler
import com.mahshad.authenticatorapp.di.IoScheduler
import com.mahshad.authenticatorapp.di.MainScheduler
import com.mahshad.authenticatorapp.home.data.home.database.ArticleDatabase
import com.mahshad.authenticatorapp.home.data.home.model.remote.toArticle
import com.mahshad.authenticatorapp.home.data.home.model.repository.Article
import com.mahshad.authenticatorapp.home.data.home.model.repository.toArticleEntity
import com.mahshad.authenticatorapp.home.network.home.ApiService
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.subjects.ReplaySubject
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
    override fun getArticles(): Single<List<Article>> {
        ///combine the single got from the db with the server
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
            }
            .subscribeOn(ioScheduler)
        val subject = ReplaySubject.create<List<Article>>()
        remoteArticleObservable.toObservable().subscribe(subject)
        return apiService
            .getRecentArticles("apple")
            .map { response ->
                if (response.isSuccessful) {
                    response.body()?.articles?.map { articleDTO ->
                        articleDTO.toArticle()
                    } ?: emptyList<Article>()
                } else {
                    emptyList<Article>()
                }
            }
            .subscribeOn(ioScheduler)
    }

    override fun updateLikedArticles(article: Article) {
        if (article.isLiked) {
            articleDatabase.Dao().insert(article.toArticleEntity())
                .subscribeOn(ioScheduler)
                .subscribe(
                    { Log.d("TAG", "successful insertion to the db") },
                    { error: Throwable ->
                        Log.e("TAG", "unsuccessful insertion to the db: ${error.message}")
                    }
                )

        } else {
            articleDatabase.Dao().delete(article.toArticleEntity())
                .subscribeOn(ioScheduler)
                .subscribe(
                    { Log.d("TAG", "successful insertion to the db") },
                    { error: Throwable ->
                        Log.e("TAG", "unsuccessful insertion to the db: ${error.message}")
                    }
                )
        }
    }
}