package com.example.converterrate.data.repository

import com.example.converterrate.data.dataSource.room.transaction.dao.TransactionDao
import com.example.converterrate.data.mapper.TransactionDboToTransactionDomainMapper
import com.example.converterrate.data.mapper.TransactionDomainToTransactionDboMapper
import com.example.converterrate.domain.entity.TransactionDomain
import com.example.currencyconverter.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * @author d.borodin
 */

class TransactionRepositoryImpl @Inject constructor(
    val dao: TransactionDao,
    val mapperDboToDomain: TransactionDboToTransactionDomainMapper,
    val mapperDomainToDbo: TransactionDomainToTransactionDboMapper,
): TransactionRepository {

    override suspend fun getListTransaction(): Flow<List<TransactionDomain>> {
        return dao.getAllFlow().map { list -> list.map { mapperDboToDomain(it) } }
    }

    override suspend fun insertTransaction(transaction: TransactionDomain) {
        dao.insertAll(mapperDomainToDbo(transaction))
    }

    override suspend fun deleteTransaction(transaction: TransactionDomain) {
        dao.deleteTransaction(mapperDomainToDbo(transaction))
    }

}