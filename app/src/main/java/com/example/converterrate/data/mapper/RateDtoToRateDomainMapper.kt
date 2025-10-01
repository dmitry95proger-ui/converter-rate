package com.example.converterrate.data.mapper

import com.example.converterrate.data.dataSource.remote.dto.RateDto
import com.example.converterrate.domain.entity.RateDomain

/**
 * @author d.borodin
 */

class RateDtoToRateDomainMapper : (RateDto) -> RateDomain  {

    override fun invoke(rateDto: RateDto): RateDomain {
        return RateDomain(
            currency = rateDto.currency,
            value = rateDto.value
        )
    }
}