package com.example.spacexassignment.model.network.responses.launches


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Telemetry(
    @SerializedName("flight_club")
    val flightClub: Any
)