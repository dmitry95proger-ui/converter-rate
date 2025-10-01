package com.example.converterrate.ui.mapper

import com.example.converterrate.domain.entity.RateDomain
import com.example.converterrate.ui.models.RateUI

/**
 * @author d.borodin
 */

class RateDomainToRateUIMapper : (RateDomain) -> RateUI  {

    override fun invoke(rateDomain: RateDomain): RateUI {
        return RateUI(
            currency = rateDomain.currency,
            value = RateUI.convertDouble(rateDomain.value)
        )
    }


}