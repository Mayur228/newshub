package com.theappmakerbuddy.newshub.data.model

import com.google.gson.annotations.SerializedName
import com.theappmakerbuddy.newshub.data.model.ApiArticle

data class News(
    @SerializedName("status")
    val status: String,

    @SerializedName("totalResults")
    val totalResults: Int,

    @SerializedName("articles")
    val articles: List<ApiArticle>
)