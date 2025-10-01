package com.example.converterrate.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room.databaseBuilder
import com.example.converterrate.data.dataSource.remote.RatesService
import com.example.converterrate.data.dataSource.remote.RemoteRatesServiceImpl
import com.example.converterrate.data.dataSource.room.ConverterDatabase
import com.example.converterrate.data.dataSource.room.ConverterDatabase.Companion.DATABASE_NAME
import com.example.converterrate.data.dataSource.room.account.dao.AccountDao
import com.example.converterrate.data.dataSource.room.transaction.dao.TransactionDao
import com.example.converterrate.data.mapper.AccountDboToAccountDomainMapper
import com.example.converterrate.data.mapper.AccountDomainToAccountDboMapper
import com.example.converterrate.data.mapper.RateDtoToRateDomainMapper
import com.example.converterrate.data.mapper.TransactionDboToTransactionDomainMapper
import com.example.converterrate.data.mapper.TransactionDomainToTransactionDboMapper
import com.example.converterrate.data.repository.AccountRepositoryImpl
import com.example.converterrate.data.repository.RateRepositoryImpl
import com.example.converterrate.data.repository.StorageRepositoryImpl
import com.example.converterrate.data.repository.TransactionRepositoryImpl
import com.example.currencyconverter.domain.repository.AccountRepository
import com.example.currencyconverter.domain.repository.RateRepository
import com.example.currencyconverter.domain.repository.StorageRepository
import com.example.currencyconverter.domain.repository.TransactionRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @author d.borodin
 */

@Module
@InstallIn(SingletonComponent:: class)
interface DataModule {
    companion object {

        @Provides
        @Singleton
        fun provideConverterDatabase(@ApplicationContext context: Context): ConverterDatabase {
            return databaseBuilder(
                context,
                ConverterDatabase::class.java, DATABASE_NAME
            ).build()
        }

        @Provides
        @Singleton
        fun provideSharedPreferences(@ApplicationContext appContext: Context): SharedPreferences =
            appContext.getSharedPreferences("shared_prefs", Context.MODE_PRIVATE)

        @Provides
        @Singleton
        fun provideAccountDao(db: ConverterDatabase): AccountDao {
            return db.accountDao()
        }

        @Provides
        @Singleton
        fun provideTransactionDao(db: ConverterDatabase): TransactionDao {
            return db.transactionDao()
        }

        @Provides
        @Singleton
        fun provideStorageRepository(sharedPrefs: SharedPreferences): StorageRepository {
            return StorageRepositoryImpl(sharedPrefs)
        }


        @Provides
        @Singleton
        fun provideRateRepository(service: RatesService, mapper: RateDtoToRateDomainMapper): RateRepository {
            return RateRepositoryImpl(ratesService = service, mapper = mapper)
        }

        @Provides
        @Singleton
        fun provideTransactionRepository(
            dao: TransactionDao,
            mapperDboToDomain: TransactionDboToTransactionDomainMapper,
            mapperDomainToDbo: TransactionDomainToTransactionDboMapper
        ): TransactionRepository {
            return TransactionRepositoryImpl(
                dao = dao,
                mapperDboToDomain = mapperDboToDomain,
                mapperDomainToDbo = mapperDomainToDbo
            )
        }

        @Provides
        @Singleton
        fun provideAccountRepository
                    (accountDao: AccountDao,
                     mapperDboToDomain: AccountDboToAccountDomainMapper,
                     mapperDomainToDbo: AccountDomainToAccountDboMapper,
        ): AccountRepository {
            return AccountRepositoryImpl(
                dao = accountDao,
                mapperDboToDomain = mapperDboToDomain,
                mapperDomainToDbo = mapperDomainToDbo
            )
        }


        @Provides
        @Singleton
        fun provideRateDtoToRateDomainMapper(): RateDtoToRateDomainMapper {
            return RateDtoToRateDomainMapper()
        }
        @Provides
        @Singleton
        fun provideAccountDboToAccountDomainMapper(): AccountDboToAccountDomainMapper {
            return AccountDboToAccountDomainMapper()
        }

        @Provides
        @Singleton
        fun provideAccountDomainToAccountDboMapper(): AccountDomainToAccountDboMapper {
            return AccountDomainToAccountDboMapper()
        }

        @Provides
        @Singleton
        fun provideTransactionDboToTransactionDomainMapper(): TransactionDboToTransactionDomainMapper {
            return TransactionDboToTransactionDomainMapper()
        }

        @Provides
        @Singleton
        fun provideTransactionDomainToTransactionDboMapper(): TransactionDomainToTransactionDboMapper {
            return TransactionDomainToTransactionDboMapper()
        }


    }

    @Binds
    @Singleton
    fun provideRatesService(impl: RemoteRatesServiceImpl): RatesService

}