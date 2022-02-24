package com.example.spacexassignment.model.network.responses.launches


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Rocket(
    @SerializedName("rocket_id")
    val rocketId: String,
    @SerializedName("rocket_name")
    val rocketName: String,
    @SerializedName("rocket_type")
    val rocketType: String,
)