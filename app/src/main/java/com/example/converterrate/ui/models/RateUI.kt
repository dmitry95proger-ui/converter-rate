package com.example.converterrate.ui.models

/**
 * @author d.borodin
 */

data class RateUI(
    val currency: String,
    val value: Double,
    val balance: Double = 0.0,
    val totalAccount: Double = 0.0
){
    companion object {
        fun convertDouble(value: Double): Double {
            return Math.round(value * 100000.0) / 100000.0
        }

        val defaultRate: RateUI = RateUI("EUR", 1.0)
    }
}