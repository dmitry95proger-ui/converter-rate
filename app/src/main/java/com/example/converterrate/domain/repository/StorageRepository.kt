package com.example.currencyconverter.domain.repository

/**
 * @author d.borodin
 */

interface StorageRepository {
    fun isFirstLaunchApp() : Boolean
}