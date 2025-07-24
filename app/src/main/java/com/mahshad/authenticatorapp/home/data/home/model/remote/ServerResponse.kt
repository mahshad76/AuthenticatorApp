package com.mahshad.authenticatorapp.home.data.home.model.remote

data class ServerResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)