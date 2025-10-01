package com.example.converterrate.data.repository

import android.content.SharedPreferences
import android.util.Log
import javax.inject.Inject
import androidx.core.content.edit
import com.example.currencyconverter.domain.repository.StorageRepository

/**
 * @author d.borodin
 */

class StorageRepositoryImpl @Inject constructor(
    private val sharedPrefs: SharedPreferences,
) : StorageRepository {

    companion object {
        private const val USERNAME_KEY = "username_key"
        private const val DEFAULT = "is_launch"

    }

    override fun isFirstLaunchApp(): Boolean {
        val result = sharedPrefs.getString(USERNAME_KEY, "") ?: ""
        Log.d("TAG", "result = $result")
        if(result != DEFAULT){
            sharedPrefs.edit { putString(USERNAME_KEY, DEFAULT) }
            return true
        }
        return false
    }
}
