package com.example.converterrate.data.repository

import com.example.converterrate.data.dataSource.remote.RatesService
import com.example.converterrate.data.mapper.RateDtoToRateDomainMapper
import com.example.converterrate.domain.entity.RateDomain
import com.example.currencyconverter.domain.repository.RateRepository
import javax.inject.Inject

/**
 * @author d.borodin
 */

class RateRepositoryImpl @Inject constructor(
    val ratesService: RatesService,
    val mapper: RateDtoToRateDomainMapper): RateRepository {
    override suspend fun getRates(code: String, amount: Double): List<RateDomain> {
        return ratesService.getRates(code, amount).map { mapper(it) }
    }
}