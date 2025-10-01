package com.example.currencyconverter.domain.useCase

import com.example.converterrate.domain.entity.RateDomain
import com.example.currencyconverter.domain.repository.RateRepository
import javax.inject.Inject

/**
 * @author d.borodin
 */

class GetRateUseCase @Inject constructor(val rateRepository: RateRepository) {
    suspend fun invoke(code: String, amount: Double): List<RateDomain> = rateRepository.getRates(code, amount)
}