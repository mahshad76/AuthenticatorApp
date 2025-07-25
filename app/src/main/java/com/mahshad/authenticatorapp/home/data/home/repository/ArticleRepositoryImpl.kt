package com.mahshad.authenticatorapp.home.data.home.repository

import com.mahshad.authenticatorapp.di.ComputationScheduler
import com.mahshad.authenticatorapp.home.data.home.model.remote.toArticle
import com.mahshad.authenticatorapp.home.data.home.model.repository.Article
import com.mahshad.authenticatorapp.home.network.home.ApiService
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticleRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    @ComputationScheduler private val computationScheduler: Scheduler
) : ArticleRepository {
    override fun getArticles(): Single<List<Article>> {
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
            .subscribeOn(computationScheduler)
    }
}