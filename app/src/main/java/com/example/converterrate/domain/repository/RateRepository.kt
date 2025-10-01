package com.example.currencyconverter.domain.repository

import com.example.converterrate.domain.entity.RateDomain

/**
 * @author d.borodin
 */

interface RateRepository {
    suspend fun getRates(code: String, amount: Double): List<RateDomain>
}