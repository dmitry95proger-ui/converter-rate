package com.example.converterrate.ui.utils

import com.example.converterrate.R

/**
 * @author d.borodin
 */

object Utils {

    fun getImage(code: String): Int = when(code){
        "EUR" -> R.drawable.jp
        "AUD" -> R.drawable.ke
        "BGN" -> R.drawable.kn
        "BRL" -> R.drawable.kr
        "CAD" -> R.drawable.la
        "CHF" -> R.drawable.lb
        "CNY" -> R.drawable.ls
        "CZK" -> R.drawable.ma
        "DKK" -> R.drawable.mf
        "GBP" -> R.drawable.mg
        "HKD" -> R.drawable.ml
        "HRK" -> R.drawable.mm
        "HUF" -> R.drawable.mo
        "IDR" -> R.drawable.mq
        "ILS" -> R.drawable.mr
        "INR" -> R.drawable.mt
        "ISK" -> R.drawable.mu
        "JPY" -> R.drawable.mv
        "KRW" -> R.drawable.mw
        "MXN" -> R.drawable.mz
        "MYR" -> R.drawable.na
        "NOK" -> R.drawable.nl
        "NZD" -> R.drawable.pa
        "PHP" -> R.drawable.pe
        "PLN" -> R.drawable.pf
        "RON" -> R.drawable.pk
        "RUB" -> R.drawable.ru
        "SEK" -> R.drawable.pm
        "SGD" -> R.drawable.pr
        "THB" -> R.drawable.pt
        "TRY" -> R.drawable.re
        "USD" -> R.drawable.ro
        "ZAR" -> R.drawable.rs
        else -> throw IllegalArgumentException("Uncorrected code parameter")
    }

    fun getDescription(code: String): String = when(code){
        "EUR" -> "Euro"
        "AUD" -> "Aust. Dollar"
        "BGN" -> "Bul. Lev"
        "BRL" -> "Br. Real"
        "CAD" -> "Can. Dollar"
        "CHF" -> "Swiss Franc"
        "CNY" -> "Renminbi"
        "CZK" -> "C. Koruna"
        "DKK" -> "D. Krone"
        "GBP" -> "B. P. Sterl."
        "HKD" -> "H.K.Dollar"
        "HRK" -> "Cr. Kuna"
        "HUF" -> "H. Forint"
        "IDR" -> "Ind. Rupiah"
        "ILS" -> "Is. Shekel"
        "INR" -> "In. Rupee"
        "ISK" -> "Ic. Króna"
        "JPY" -> "J. Yen"
        "KRW" -> "S. K. Won"
        "MXN" -> "Mex. Peso"
        "MYR" -> "Mal.Ringgit"
        "NOK" -> "Nor. Krone"
        "NZD" -> "N. Z. Dollar"
        "PHP" -> "Ph. Peso"
        "PLN" -> "P. Złoty"
        "RON" -> "R. Leu"
        "RUB" -> "Russ. Ruble"
        "SEK" -> "Sw. Krona"
        "SGD" -> "Sin. Dollar"
        "THB" -> "Thai Baht"
        "TRY" -> "Tur. Lira"
        "USD" -> "U. S. Dollar"
        "ZAR" -> "S. A. Rand"
        else -> {
            throw IllegalArgumentException("Uncorrected code parameter")
        }
    }
}