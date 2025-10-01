package com.example.converterrate.data.dataSource.room.transaction.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.converterrate.data.dataSource.room.transaction.dbo.TransactionDbo
import kotlinx.coroutines.flow.Flow

/**
 * @author d.borodin
 */

@Dao
interface TransactionDao {
    @Insert
    suspend fun insertAll(vararg transactions: TransactionDbo)

    @Query("SELECT * FROM ${TransactionDbo.TRANSACTION_TABLE_NAME}")
    fun getAllFlow(): Flow<List<TransactionDbo>>

    @Delete
    suspend fun deleteTransaction(transactionDbo: TransactionDbo)
}