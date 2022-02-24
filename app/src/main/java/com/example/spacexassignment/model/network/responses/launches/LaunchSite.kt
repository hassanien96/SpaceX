package com.example.spacexassignment.model.network.responses.launches


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class LaunchSite(
    @SerializedName("site_id")
    val siteId: String,
    @SerializedName("site_name")
    val siteName: String,
    @SerializedName("site_name_long")
    val siteNameLong: String
)