package com.example.converterrate.ui.mapper

import com.example.converterrate.domain.entity.AccountDomain
import com.example.converterrate.ui.models.AccountUI

/**
 * @author d.borodin
 */

class AccountUIToAccountDomainMapper : (AccountUI) -> AccountDomain  {

    override fun invoke(accountUI: AccountUI): AccountDomain {
        return AccountDomain(
            code = accountUI.code,
            amount = accountUI.amount
        )
    }
}