package com.theappmakerbuddy.newshub.data.model

import com.google.gson.annotations.SerializedName
import com.theappmakerbuddy.newshub.data.model.ApiSource

data class Sources(
    @SerializedName("status")
    val status: String,

    @SerializedName("sources")
    val sources: List<ApiSource>
)
