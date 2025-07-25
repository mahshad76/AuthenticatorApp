package com.mahshad.authenticatorapp.home.data.home.model.remote

import com.google.gson.annotations.SerializedName

data class ServerResponseDTO(
    @SerializedName("articles")
    val articles: List<ArticleDTO>?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("totalResults")
    val totalResults: Int?
)