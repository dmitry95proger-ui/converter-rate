package com.example.converterrate.data.dataSource.remote

import com.example.converterrate.data.dataSource.remote.dto.RateDto

/**
 * @author d.borodin
 */

interface RatesService {
    suspend fun getRates(
        baseCurrencyCode: String,
        amount: Double
    ): List<RateDto>
}