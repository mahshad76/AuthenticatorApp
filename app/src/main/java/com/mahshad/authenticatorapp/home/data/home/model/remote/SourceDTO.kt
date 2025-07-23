package com.mahshad.authenticatorapp.home.data.home.model.remote

import com.google.gson.annotations.SerializedName

data class SourceDTO(
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?
)