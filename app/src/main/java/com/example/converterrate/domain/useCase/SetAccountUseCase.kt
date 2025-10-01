package com.example.currencyconverter.domain.useCase

import com.example.converterrate.domain.entity.AccountDomain
import com.example.currencyconverter.domain.repository.AccountRepository
import javax.inject.Inject

/**
 * @author d.borodin
 */

class SetAccountUseCase @Inject constructor(val accountRepository: AccountRepository) {
    suspend fun invoke(account: AccountDomain) = accountRepository.setAccount(account)
}