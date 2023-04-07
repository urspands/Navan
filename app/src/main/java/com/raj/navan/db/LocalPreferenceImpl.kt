package com.raj.navan.db

import android.content.Context
import android.content.Context.MODE_PRIVATE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalPreferenceImpl(private val context: Context) : LocalPreference {
    private val _sharedPref = context.getSharedPreferences("LOCAL-PREF", MODE_PRIVATE)
    override suspend fun saveBoolean(key: String, value: Boolean) {
        withContext(Dispatchers.IO) {
            _sharedPref.edit().putBoolean(key, value).apply()
        }
    }

    override suspend fun getBoolean(key: String): Boolean {
        return withContext(Dispatchers.IO) {
            _sharedPref.getBoolean(key, false)
        }
    }
}