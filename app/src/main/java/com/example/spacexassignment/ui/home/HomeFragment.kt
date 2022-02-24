package com.example.spacexassignment.ui.home

import android.os.Bundle
import android.view.*
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spacexassignment.R
import com.example.spacexassignment.common.base.ui.BaseFragment
import com.example.spacexassignment.common.base.wrappers.ResponseWrapper
import com.example.spacexassignment.common.utils.PaginationScrollListener
import com.example.spacexassignment.common.utils.ext.init
import com.example.spacexassignment.common.utils.ext.navigateWith
import com.example.spacexassignment.common.utils.ext.toPriceAmount
import com.example.spacexassignment.databinding.FragmentHomeBinding
import com.example.spacexassignment.model.dto.Filter
import com.example.spacexassignment.model.network.responses.companyInfo.CompanyInfoResponse
import com.example.spacexassignment.model.network.responses.launches.LaunchesResponse
import com.example.spacexassignment.ui.adapters.LaunchesAdapter
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    companion object {
        const val RESULT_FROM_FILTER_DIALOG = "RESULT_FROM_FILTER_DIALOG"
        const val ASCENDING = "asc"
        const val DESCENDING = "desc"
    }
    override val _BindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate

    var isLastPage: Boolean = false
    var isLoading: Boolean = false
    var offset: Int =0
    var launchYear :Int?=null
    var isSuccessLaunch:Boolean?=null
    var order:String?= null
    private val homeViewModel by viewModels<HomeViewModel>()

    private val launchesAdapter by lazy {
        LaunchesAdapter {
            if (it.links.isLinksAvailable()) {
                HomeFragmentDirections.actionLaunchesFragmentToOptionsDialog(it.links).navigateWith(this)
            }else{
                Snackbar.make(binding.root,getString(R.string.no_links),Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeViewModel.getCompanyInfo()
        homeViewModel.getLaunches(offset = offset,launchYear = launchYear,isSuccess = isSuccessLaunch,order = order)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        binding.apply {
            rv.init(launchesAdapter)
            btnRetry.setOnClickListener { onBtnRetryClick() }
            btnClear.setOnClickListener { onBtnClearClick() }
        }

        val currentBackStackEntry = findNavController().currentBackStackEntry
        val savedStateHandle = currentBackStackEntry?.savedStateHandle
        savedStateHandle?.getLiveData<Filter>(RESULT_FROM_FILTER_DIALOG)
            ?.observe(currentBackStackEntry, Observer { result ->
                binding.noResultContainer.isVisible=false
                launchesAdapter.clearItems()
                offset=0
                isLastPage=false
                launchYear=result.launchYear
                isSuccessLaunch=result.isSuccessfulLaunch
                order = if (result.isAscending) ASCENDING else DESCENDING

                homeViewModel.getLaunches(offset = offset,launchYear = launchYear,isSuccess = isSuccessLaunch,order = order)

            })



        homeViewModel.companyInfoObservable.observe(viewLifecycleOwner) {

            when (it) {
                is ResponseWrapper.Loading -> onCompanyInfoLoading()
                is ResponseWrapper.Failure -> onFailure()
                is ResponseWrapper.LocalFailure -> onLocalFailure()
                is ResponseWrapper.Success -> onCompanyInfoSuccess(it.value)
            }
        }

        homeViewModel.launchesObservable.observe(viewLifecycleOwner) {
            binding.progressBar.isVisible = it is ResponseWrapper.Loading
            when (it) {
                is ResponseWrapper.Loading -> onLaunchesLoading()
                is ResponseWrapper.Failure -> onFailure()
                is ResponseWrapper.LocalFailure -> onLocalFailure()
                is ResponseWrapper.Success -> onLaunchesSuccess(it.value)
            }
        }

        binding.rv.addOnScrollListener(object : PaginationScrollListener(binding.rv.layoutManager as LinearLayoutManager){
            override fun isLastPage(): Boolean {
                return isLastPage
            }

            override fun isLoading(): Boolean {
                return isLoading
            }

            override fun loadMoreItems() {
                isLoading=true
                getNextLaunches()
            }

        })



    }

    private fun getNextLaunches(){
        offset+=10
        homeViewModel.getLaunches(offset = offset,launchYear = launchYear,isSuccess = isSuccessLaunch,order = order)

    }

    private fun onLocalFailure() {
        binding.apply {
            errorContainer.isVisible=true
        }
    }

    private fun onFailure() {
        binding.apply {
            errorContainer.isVisible=true
        }
    }

    private fun onLaunchesSuccess(launches: LaunchesResponse) {
        binding.tvLaunchesTitle.isVisible=true
        isLoading = false
        binding.apply {
            errorContainer.isVisible=false
        }
        if (launches.isNotEmpty()) {
            launchesAdapter.setItemsPaginated(launches)
            println("ADDED_DATA${launchesAdapter.itemCount}")

        }else{
            if (launchesAdapter.itemCount>0) {
                isLastPage = true
                println("ITEMS_SIZE${launchesAdapter.itemCount}")
            }else{
                binding.noResultContainer.isVisible=true
            }
        }
    }

    private fun onLaunchesLoading(){

    }
    private fun onCompanyInfoSuccess(info:CompanyInfoResponse){
        binding.tvCompanyTitle.isVisible=true
        info.apply {
            binding.tvCompanyInfo.text = "$name was founded by $founder in " +
                    "$founded. It has now $employees employees, $launchSites " +
                    "launch sites, and is valued at USD ${valuation.toString().toPriceAmount()}"
        }

    }
    private fun onCompanyInfoLoading(){
    }

    private fun onActionFilterClick(){
        HomeFragmentDirections.actionLaunchesFragmentToFilterDialog().navigateWith(this)
    }

    private fun onBtnRetryClick(){
        homeViewModel.apply {
            getCompanyInfo()
            getLaunches(offset = offset,launchYear = launchYear,isSuccess = isSuccessLaunch,order = order)
        }
    }

    private fun onBtnClearClick(){
        offset=0
        isLastPage=false
        launchYear=null
        isSuccessLaunch=null
        order = null
        binding.noResultContainer.isVisible=false
        homeViewModel.getLaunches(offset = offset,launchYear = launchYear,isSuccess = isSuccessLaunch,order = order)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.home_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_action_filter -> onActionFilterClick()
        }
        return super.onOptionsItemSelected(item)
    }
}