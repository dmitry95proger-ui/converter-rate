package com.example.currencyconverter.domain.repository

import com.example.converterrate.domain.entity.AccountDomain
import kotlinx.coroutines.flow.Flow

/**
 * @author d.borodin
 */

interface AccountRepository {
    suspend fun getListAccounts(): Flow<List<AccountDomain>>
    suspend fun setAccount(account: AccountDomain)
}