package com.example.spacexassignment.ui.home.filterDialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.navigation.fragment.findNavController
import com.example.spacexassignment.R
import com.example.spacexassignment.common.base.ui.BaseBottomSheetDialog
import com.example.spacexassignment.databinding.DialogFilterBinding
import com.example.spacexassignment.model.dto.Filter
import com.example.spacexassignment.ui.home.HomeFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FilterDialog : BaseBottomSheetDialog<DialogFilterBinding>() {
    override val _bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> DialogFilterBinding
        get() = DialogFilterBinding::inflate

    val filter: Filter = Filter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            btnApply.setOnClickListener { onBtnApplyClicked() }
        }

    }

    private fun onBtnApplyClicked() {
        val btnSort: RadioButton? = view?.findViewById(binding.radioSort.checkedRadioButtonId)
        val btnLaunchStatus: RadioButton? = view?.findViewById(binding.radioLaunchStatus.checkedRadioButtonId)

        when (btnSort?.id) {
            R.id.btn_ascending -> filter.isAscending = true
            R.id.btn_descending -> filter.isAscending = false
        }
        when (btnLaunchStatus?.id) {
            R.id.btn_success_launch -> filter.isSuccessfulLaunch = true
            R.id.btn_failed_launch -> filter.isSuccessfulLaunch = false
        }
        binding.etYear.text?.toString()?.let {
            if (it.length > 0) filter.launchYear = it.toInt()
        }


        val savedStateHandle = findNavController().previousBackStackEntry?.savedStateHandle
        savedStateHandle?.set(HomeFragment.RESULT_FROM_FILTER_DIALOG, filter)

        findNavController().navigateUp()
    }

}


