package com.antisnusbolaget.slutasnusa2.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.antisnusbolaget.slutasnusa2.viewmodel.UserData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private const val DATA_STORE_NAME = "userToken"

class DataStore(private val context: Context) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATA_STORE_NAME)
        private val USER_TOKEN_DATE = longPreferencesKey("user_token")
        private val USER_TOKEN_COST = intPreferencesKey("user_token_cost")
        private val USER_TOKEN_UNITS = intPreferencesKey("user_token_units")
    }

    fun isKeyStored(): Flow<Boolean> =
        context.dataStore.data.map { preference ->
            preference.contains(USER_TOKEN_COST)
        }

    val getUserData: Flow<UserData> = context.dataStore.data.map { preferences ->
        UserData(
            dateWhenQuit = preferences[USER_TOKEN_DATE] ?: 0,
            costPerUnit = preferences[USER_TOKEN_COST] ?: 0,
            units = preferences[USER_TOKEN_UNITS] ?: 0,
        )
    }

    suspend fun setDateWhenQuitInMillis(quitDate: Long) {
        context.dataStore.edit { preferences ->
            preferences[USER_TOKEN_DATE] = quitDate
        }
    }

    suspend fun setAmountOfUnits(units: Int) {
        context.dataStore.edit { preferences ->
            preferences[USER_TOKEN_UNITS] = units
        }
    }

    suspend fun setCost(cost: Int) {
        context.dataStore.edit { preferences ->
            preferences[USER_TOKEN_COST] = cost
        }
    }
}
