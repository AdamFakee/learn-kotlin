package com.adamfakee.learnkotlin.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// Khởi tạo datastore instance
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "app_prefs")

class DataStoreManager(private val context: Context) {

    // Dùng thay static variables
    companion object {
        val ONBOARDING_COMPLETED_KEY = booleanPreferencesKey("onboarding_completed")
    }

    // generic func lấy data được định nghĩa theo preference key
    fun <T> getPreferenceFlow(key: Preferences.Key<T>, defaultValue: T): Flow<T> {
        return context.dataStore.data.map { preferences -> preferences[key] ?: defaultValue }
    }

    // generic func lưu data được định nghĩa theo preference key
    suspend fun <T> savePreference(key: Preferences.Key<T>, value: T) {
        context.dataStore.edit { preferences -> preferences[key] = value }
    }
}
