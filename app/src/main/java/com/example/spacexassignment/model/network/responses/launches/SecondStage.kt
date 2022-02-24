package com.example.spacexassignment.model.network.responses.launches


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class SecondStage(
    @SerializedName("block")
    val block: Int,
    @SerializedName("payloads")
    val payloads: List<Payload>
)