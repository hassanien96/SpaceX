package com.example.spacexassignment.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import com.example.spacexassignment.common.base.wrappers.ResponseWrapper
import com.example.spacexassignment.common.utils.ext.asLiveData
import com.example.spacexassignment.model.repositories.HomeRepo
import com.example.spacexassignment.model.network.responses.companyInfo.CompanyInfoResponse
import com.example.spacexassignment.model.network.responses.launches.LaunchesResponse
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepo: HomeRepo
    ) : ViewModel() {

    private val _launchesObservable = MutableLiveData<ResponseWrapper<LaunchesResponse>>()
    private val _companyInfoObservable = MutableLiveData<ResponseWrapper<CompanyInfoResponse>>()

    val launchesObservable get() = _launchesObservable.asLiveData()
    val companyInfoObservable get() = _companyInfoObservable.asLiveData()

    private val TAG = "TAG"

    fun getLaunches(offset:Int,launchYear:Int?=null,isSuccess:Boolean?=null,order:String?=null) {
        viewModelScope.launch {
            homeRepo.getLaunches(limit = 10,offset = offset,launchYear = launchYear,isSuccess = isSuccess,order = order).collect {
                Log.d(TAG, "getLaunches: $it")
                _launchesObservable.postValue(it)
            }
        }
    }

    fun getCompanyInfo() {
        viewModelScope.launch {
            homeRepo.getCompanyInfo().collect {
                Log.d(TAG, "getCompanyInfo: $it")
                _companyInfoObservable.postValue(it)
            }
        }
    }
}