package com.example.spacexassignment.ui

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import dagger.hilt.android.AndroidEntryPoint
import com.example.spacexassignment.common.base.ui.BaseActivity
import com.example.spacexassignment.databinding.ActivityMainBinding


@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    override val _BindingInflater: (LayoutInflater) -> ActivityMainBinding
            by lazy { ActivityMainBinding::inflate }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);


        initNavController(binding.fragmentContainerView)
        initToolbar(binding.toolbar)


    }

    override fun onBackPressed() {
        if (navController.previousBackStackEntry == null)
            finish()
        else {
            super.onBackPressed()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }
}