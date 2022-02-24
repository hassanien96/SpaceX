package com.example.spacexassignment.common.utils.ext

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes


fun ViewGroup.inflateRvItem(@LayoutRes itemRes: Int): View {
    return LayoutInflater.from(this.context)
        .inflate(itemRes, this, false)
}



