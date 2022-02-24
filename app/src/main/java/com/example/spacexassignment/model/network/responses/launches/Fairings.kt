package com.example.spacexassignment.model.network.responses.launches


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Fairings(
    @SerializedName("recovered")
    val recovered: Any,
    @SerializedName("recovery_attempt")
    val recoveryAttempt: Any,
    @SerializedName("reused")
    val reused: Any,
    @SerializedName("ship")
    val ship: Any
)