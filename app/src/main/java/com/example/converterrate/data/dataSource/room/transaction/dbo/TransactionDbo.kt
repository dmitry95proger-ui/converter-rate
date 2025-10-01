package com.example.converterrate.data.dataSource.room.transaction.dbo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.converterrate.data.dataSource.room.converter.Converters
import com.example.converterrate.data.dataSource.room.transaction.dbo.TransactionDbo.Companion.TRANSACTION_TABLE_NAME
import java.time.LocalDateTime

/**
 * @author d.borodin
 */

@Entity(tableName = TRANSACTION_TABLE_NAME)
@TypeConverters(Converters::class)
data class TransactionDbo (
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @ColumnInfo(name = "currency_code_from")
    val from: String,
    @ColumnInfo(name = "currency_code_to")
    val to: String,
    @ColumnInfo(name = "amount_from")
    val fromAmount: Double,
    @ColumnInfo(name = "amount_to")
    val toAmount: Double,
    val dateTime: LocalDateTime,
){
    companion object {
        const val TRANSACTION_TABLE_NAME = "transaction_table"
    }
}