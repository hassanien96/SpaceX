package com.example.spacexassignment.model.network.api

import com.example.spacexassignment.model.network.responses.companyInfo.CompanyInfoResponse
import com.example.spacexassignment.model.network.responses.launches.LaunchesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SpaceXApi {

    @GET("info")
    suspend fun getSpacexInfo(): Response<CompanyInfoResponse>

    @GET("launches")
    suspend fun getAllLaunches(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
        @Query("launch_year") launchYear: Int? = null,
        @Query("launch_success") isSuccess: Boolean? = null,
        @Query("order") order: String? = null
    ): Response<LaunchesResponse>

}