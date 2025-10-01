package com.example.currencyconverter.domain.useCase

import com.example.converterrate.domain.entity.TransactionDomain
import com.example.currencyconverter.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * @author d.borodin
 */

class GetTransactionUseCase @Inject constructor(val transactionRepository: TransactionRepository) {
    suspend fun invoke(): Flow<List<TransactionDomain>> = transactionRepository.getListTransaction()
}