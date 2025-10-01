package com.example.currencyconverter.domain.useCase

import com.example.currencyconverter.domain.repository.StorageRepository

import javax.inject.Inject

/**
 * @author d.borodin
 */

class IsFirstLaunchUseCase @Inject constructor(
    private val storageRepository: StorageRepository
){
    fun invoke() : Boolean {
        return storageRepository.isFirstLaunchApp()
    }
}

