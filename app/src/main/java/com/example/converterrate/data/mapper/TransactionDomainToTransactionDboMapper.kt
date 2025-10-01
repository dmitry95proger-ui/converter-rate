package com.example.converterrate.data.mapper

import com.example.converterrate.data.dataSource.room.transaction.dbo.TransactionDbo
import com.example.converterrate.domain.entity.TransactionDomain

/**
 * @author d.borodin
 */

class TransactionDomainToTransactionDboMapper : (TransactionDomain) -> TransactionDbo {

    override fun invoke(transactionDbo: TransactionDomain): TransactionDbo {
        return TransactionDbo(
            id = transactionDbo.id,
            from = transactionDbo.from,
            to = transactionDbo.to,
            fromAmount = transactionDbo.fromAmount,
            toAmount = transactionDbo.toAmount,
            dateTime = transactionDbo.dateTime,
        )
    }
}