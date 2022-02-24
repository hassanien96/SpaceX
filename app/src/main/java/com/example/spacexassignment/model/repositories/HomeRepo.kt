package com.example.spacexassignment.model.repositories

import com.example.spacexassignment.common.base.interactors.BaseNetworkInteractor
import com.example.spacexassignment.model.network.api.SpaceXApi

class HomeRepo(
    private val api: SpaceXApi
) : BaseNetworkInteractor() {

    fun getCompanyInfo() = safeApiCall {
        api.getSpacexInfo()
    }

    fun getLaunches(
        limit: Int,
        offset: Int,
        launchYear: Int? = null,
        isSuccess: Boolean? = null,
        order: String? = null
    ) = safeApiCall {
        api.getAllLaunches(
            limit = limit,
            offset = offset,
            launchYear = launchYear,
            isSuccess = isSuccess,
            order = order
        )
    }


}
