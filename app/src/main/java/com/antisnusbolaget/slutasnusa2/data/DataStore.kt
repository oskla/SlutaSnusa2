package com.antisnusbolaget.slutasnusa2.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private const val DATA_STORE_NAME = "userToken"

class DataStore(private val context: Context) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATA_STORE_NAME)
        private val USER_TOKEN_DATE = stringPreferencesKey("user_token")
        private val USER_TOKEN_COST = stringPreferencesKey("user_token_cost")
        private val USER_TOKEN_UNITS = stringPreferencesKey("user_token_units")
    }

    val getQuitDate: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[USER_TOKEN_DATE] ?: ""
    }

    val getCost: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[USER_TOKEN_COST] ?: ""
    }

    val getUnits: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[USER_TOKEN_UNITS] ?: ""
    }

    suspend fun setDateWhenQuitInMillis(quitDate: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_TOKEN_DATE] = quitDate
        }
    }

    suspend fun setAmountOfUnits(units: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_TOKEN_UNITS] = units
        }
    }

    suspend fun setCost(cost: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_TOKEN_COST] = cost
        }
    }
}
