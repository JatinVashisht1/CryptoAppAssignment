package com.example.cryptoapp.common.utils

import kotlin.math.pow
import kotlin.math.roundToInt

/**
 * extension function to round off the given double into given places after decimal
 * @param roundToPlaces places you want to round off your double
 * */
fun Double.roundTo(roundToPlaces: Int): Double {
    if (roundToPlaces < 0) return this

    val factor = 10.0.pow(roundToPlaces.toDouble())
    return (this * factor).roundToInt() / factor
}