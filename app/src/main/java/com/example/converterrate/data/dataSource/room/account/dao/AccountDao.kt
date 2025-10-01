package com.example.converterrate.data.dataSource.room.account.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.converterrate.data.dataSource.room.account.dbo.AccountDbo
import kotlinx.coroutines.flow.Flow

/**
 * @author d.borodin
 */

@Dao
interface AccountDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg accounts: AccountDbo)

    @Query("SELECT * FROM ${AccountDbo.ACCOUNT_TABLE_NAME}")
    suspend fun getAll(): List<AccountDbo>

    @Query("SELECT * FROM ${AccountDbo.ACCOUNT_TABLE_NAME}")
    fun getAllAsFlow(): Flow<List<AccountDbo>>
}