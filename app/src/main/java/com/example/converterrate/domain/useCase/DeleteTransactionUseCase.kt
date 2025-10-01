package com.example.currencyconverter.domain.useCase

import com.example.converterrate.domain.entity.TransactionDomain
import com.example.currencyconverter.domain.repository.TransactionRepository
import javax.inject.Inject

/**
 * @author d.borodin
 */

class DeleteTransactionUseCase @Inject constructor(val transactionRepository: TransactionRepository) {
    suspend fun invoke(transaction: TransactionDomain) = transactionRepository.deleteTransaction(transaction)
}