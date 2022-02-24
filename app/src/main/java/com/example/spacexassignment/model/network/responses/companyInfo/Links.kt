package com.example.spacexassignment.model.network.responses.companyInfo


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Links(
    @SerializedName("elon_twitter")
    val elonTwitter: String,
    @SerializedName("flickr")
    val flickr: String,
    @SerializedName("twitter")
    val twitter: String,
    @SerializedName("website")
    val website: String
)