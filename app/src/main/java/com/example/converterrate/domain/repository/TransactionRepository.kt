package com.example.currencyconverter.domain.repository

import com.example.converterrate.domain.entity.TransactionDomain
import kotlinx.coroutines.flow.Flow

/**
 * @author d.borodin
 */

interface TransactionRepository {
    suspend fun getListTransaction(): Flow<List<TransactionDomain>>
    suspend fun insertTransaction(transaction: TransactionDomain)
    suspend fun deleteTransaction(transaction: TransactionDomain)
}