package com.example.converterrate.di

import com.example.currencyconverter.domain.repository.AccountRepository
import com.example.currencyconverter.domain.repository.RateRepository
import com.example.currencyconverter.domain.repository.StorageRepository
import com.example.currencyconverter.domain.repository.TransactionRepository
import com.example.currencyconverter.domain.useCase.DeleteTransactionUseCase
import com.example.currencyconverter.domain.useCase.GetAccountUseCase
import com.example.currencyconverter.domain.useCase.GetRateUseCase
import com.example.currencyconverter.domain.useCase.GetTransactionUseCase
import com.example.currencyconverter.domain.useCase.MoveSelectedRateToUpUseCase
import com.example.currencyconverter.domain.useCase.SetAccountUseCase
import com.example.currencyconverter.domain.useCase.SetTransactionUseCase
import com.example.currencyconverter.domain.useCase.IsFirstLaunchUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @author d.borodin
 */

@Module
@InstallIn(SingletonComponent:: class)
interface DomainModule {
    companion object {


        @Provides
        @Singleton
        fun provideGetRateUseCase(rateRepository: RateRepository): GetRateUseCase {
            return GetRateUseCase(rateRepository = rateRepository)
        }

        @Provides
        @Singleton
        fun provideMoveSelectedRateToUpUseCase(): MoveSelectedRateToUpUseCase {
            return MoveSelectedRateToUpUseCase()
        }

        @Provides
        @Singleton
        fun provideGetAccountUseCase(accountRepository: AccountRepository): GetAccountUseCase {
            return GetAccountUseCase(accountRepository)
        }
        @Provides
        @Singleton
        fun provideSetAccountUseCase(accountRepository: AccountRepository): SetAccountUseCase {
            return SetAccountUseCase(accountRepository)
        }

        @Provides
        @Singleton
        fun provideGetTransactionUseCase(transactionRepository: TransactionRepository): GetTransactionUseCase {
            return GetTransactionUseCase(transactionRepository)
        }

        @Provides
        @Singleton
        fun provideSetTransactionUseCase(transactionRepository: TransactionRepository): SetTransactionUseCase {
            return SetTransactionUseCase(transactionRepository)
        }
        @Provides
        @Singleton
        fun provideDeleteTransactionUseCase(transactionRepository: TransactionRepository): DeleteTransactionUseCase {
            return DeleteTransactionUseCase(transactionRepository)
        }

        @Provides
        @Singleton
        fun provideIsFirstLaunchUseCase(storageRepository: StorageRepository): IsFirstLaunchUseCase {
            return IsFirstLaunchUseCase(storageRepository)
        }
    }
}