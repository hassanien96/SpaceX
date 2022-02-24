package com.example.spacexassignment.model.dto

import androidx.annotation.Keep
import java.io.Serializable
@Keep
data class Filter(
    var launchYear: Int?=null,
    var isAscending:Boolean=true,
    var isSuccessfulLaunch:Boolean=true
):Serializable