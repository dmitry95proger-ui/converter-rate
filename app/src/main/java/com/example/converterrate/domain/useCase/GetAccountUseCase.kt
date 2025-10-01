package com.example.currencyconverter.domain.useCase

import com.example.converterrate.domain.entity.AccountDomain
import com.example.currencyconverter.domain.repository.AccountRepository
import kotlinx.coroutines.flow.Flow

import javax.inject.Inject

/**
 * @author d.borodin
 */

class GetAccountUseCase @Inject constructor(val accountRepository: AccountRepository) {
    suspend fun invoke(): Flow<List<AccountDomain>> = accountRepository.getListAccounts()
}