package com.example.converterrate.ui.mapper

import com.example.converterrate.domain.entity.RateDomain
import com.example.converterrate.ui.models.RateUI

/**
 * @author d.borodin
 */

class RateUIToRateDomainMapper : (RateUI) -> RateDomain  {

    override fun invoke(rateUI: RateUI): RateDomain {
        return RateDomain(
            currency = rateUI.currency,
            value = rateUI.value
        )
    }
}