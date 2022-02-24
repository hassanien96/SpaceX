package com.example.spacexassignment.model.network.responses.companyInfo


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Headquarters(
    @SerializedName("address")
    val address: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("state")
    val state: String
)