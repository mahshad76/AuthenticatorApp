package com.mahshad.authenticatorapp.home.network

import com.mahshad.authenticatorapp.home.model.response.Root
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("everything")
    fun getRecentArticles(
        @Query("q") query: String
    ): Single<Response<List<Root?>>>
}