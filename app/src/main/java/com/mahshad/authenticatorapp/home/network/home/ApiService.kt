package com.mahshad.authenticatorapp.home.network.home

import com.mahshad.authenticatorapp.home.data.home.model.remote.ServerResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("everything")
    fun getRecentArticles(
        @Query("q") query: String
    ): Single<Response<ServerResponse?>>
}