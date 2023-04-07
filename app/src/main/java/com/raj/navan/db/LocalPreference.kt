package com.raj.navan.db

interface LocalPreference {
    suspend fun saveBoolean(key: String, value: Boolean)
    suspend fun getBoolean(key: String): Boolean
}