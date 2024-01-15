package com.example.cryptoapp.common.utils

import android.util.Log
import com.example.cryptoapp.BuildConfig

object Logger {
    fun debug(tag: String, value: String) {
        if (!BuildConfig.DEBUG) return

        Log.d(tag, value)
    }

    fun error(tag: String, value: String) {
        if (!BuildConfig.DEBUG) return

        Log.e(tag, value)
    }

    fun warning(tag: String, value: String) {
        if (!BuildConfig.DEBUG) return

        Log.w(tag, value)
    }
}