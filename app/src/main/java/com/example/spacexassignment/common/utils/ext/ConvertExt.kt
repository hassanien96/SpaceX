package com.example.spacexassignment.common.utils.ext

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

fun <T> MutableLiveData<T>.asLiveData() = this as LiveData<T>

fun Date.formatToDateDefaults(): String {
    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return sdf.format(this)
}

fun Date.formatToTimeDefaults(): String {
    val sdf = SimpleDateFormat("hh:mm:ss aa", Locale.getDefault())
    return sdf.format(this)
}

fun String.toPriceAmount(): String {
    val dec = DecimalFormat("###,###,###.00")
    return dec.format(this.toDouble())
}