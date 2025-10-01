package com.example.converterrate.domain.entity

import java.time.LocalDateTime

/**
 * @author d.borodin
 */

data class TransactionDomain (
    val id: Int? = null,
    val from: String,
    val to: String,
    val fromAmount: Double,
    val toAmount: Double,
    val dateTime: LocalDateTime,
)