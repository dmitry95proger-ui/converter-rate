package com.example.converterrate.data.dataSource.room.account.dbo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.converterrate.data.dataSource.room.account.dbo.AccountDbo.Companion.ACCOUNT_TABLE_NAME

/**
 * @author d.borodin
 */

@Entity(tableName = ACCOUNT_TABLE_NAME)
data class AccountDbo(
    @PrimaryKey
    @ColumnInfo(name = "currency_code")
    val code: String,
    val amount: Double
){
    companion object {
        const val ACCOUNT_TABLE_NAME = "account_table"
    }
}