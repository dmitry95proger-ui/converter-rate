package com.example.converterrate.data.mapper

import com.example.converterrate.data.dataSource.room.account.dbo.AccountDbo
import com.example.converterrate.domain.entity.AccountDomain

/**
 * @author d.borodin
 */

class AccountDboToAccountDomainMapper : (AccountDbo) -> AccountDomain  {

    override fun invoke(accountDbo: AccountDbo): AccountDomain {
        return AccountDomain(
            code = accountDbo.code,
            amount = accountDbo.amount
        )
    }
}