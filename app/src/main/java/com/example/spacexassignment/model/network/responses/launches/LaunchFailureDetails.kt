package com.example.spacexassignment.model.network.responses.launches


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class LaunchFailureDetails(
    @SerializedName("altitude")
    val altitude: Int,
    @SerializedName("reason")
    val reason: String,
    @SerializedName("time")
    val time: Int
)