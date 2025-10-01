package com.example.converterrate.ui.mapper

import com.example.converterrate.domain.entity.AccountDomain
import com.example.converterrate.ui.models.AccountUI

/**
 * @author d.borodin
 */

class AccountDomainToAccountUIMapper : (AccountDomain) -> AccountUI  {

    override fun invoke(accountDomain: AccountDomain): AccountUI {
        return AccountUI(
            code = accountDomain.code,
            amount = accountDomain.amount
        )
    }
}