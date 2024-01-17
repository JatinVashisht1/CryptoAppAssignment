package com.example.cryptoapp.presentation.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

fun getDateTimeStringFromMillis(millisTime: Long): String {
    val dateFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())
    val date = Date(millisTime)
    return dateFormat.format(date)
}