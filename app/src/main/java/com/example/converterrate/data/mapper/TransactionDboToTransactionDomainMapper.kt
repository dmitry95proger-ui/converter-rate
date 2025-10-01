package com.example.converterrate.data.mapper

import com.example.converterrate.data.dataSource.room.transaction.dbo.TransactionDbo
import com.example.converterrate.domain.entity.TransactionDomain

/**
 * @author d.borodin
 */

class TransactionDboToTransactionDomainMapper : (TransactionDbo) -> TransactionDomain {

    override fun invoke(transactionDbo: TransactionDbo): TransactionDomain {
        return TransactionDomain(
            id = transactionDbo.id,
            from = transactionDbo.from,
            to = transactionDbo.to,
            fromAmount = transactionDbo.fromAmount,
            toAmount = transactionDbo.toAmount,
            dateTime = transactionDbo.dateTime,
        )
    }
}