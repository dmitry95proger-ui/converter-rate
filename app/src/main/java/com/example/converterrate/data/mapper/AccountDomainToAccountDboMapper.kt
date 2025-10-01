package com.example.converterrate.data.mapper

import com.example.converterrate.data.dataSource.room.account.dbo.AccountDbo
import com.example.converterrate.domain.entity.AccountDomain

/**
 * @author d.borodin
 */

class AccountDomainToAccountDboMapper : (AccountDomain) -> AccountDbo  {

    override fun invoke(accountDbo: AccountDomain): AccountDbo {
        return AccountDbo(
            code = accountDbo.code,
            amount = accountDbo.amount
        )
    }
}