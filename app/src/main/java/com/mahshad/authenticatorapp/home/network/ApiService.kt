package com.mahshad.authenticatorapp.home.network

import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {
    @GET
    fun getRecentArticles(): Single<>
}