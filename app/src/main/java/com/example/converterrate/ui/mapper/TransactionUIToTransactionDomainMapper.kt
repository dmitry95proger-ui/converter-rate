package com.example.converterrate.ui.mapper

import com.example.converterrate.domain.entity.TransactionDomain
import com.example.converterrate.ui.models.TransactionUI

/**
 * @author d.borodin
 */

class TransactionUIToTransactionDomainMapper : (TransactionUI) -> TransactionDomain {

    override fun invoke(transactionUI: TransactionUI): TransactionDomain {
        return TransactionDomain(
            id = transactionUI.id,
            from = transactionUI.from,
            to = transactionUI.to,
            fromAmount = transactionUI.fromAmount,
            toAmount = transactionUI.toAmount,
            dateTime = transactionUI.dateTime,
        )
    }
}