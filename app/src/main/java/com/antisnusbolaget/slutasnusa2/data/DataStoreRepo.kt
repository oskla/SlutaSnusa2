package com.antisnusbolaget.slutasnusa2.data

import android.content.Context
import androidx.datastore.preferences.core.emptyPreferences
import com.antisnusbolaget.slutasnusa2.viewmodel.onboarding.UserData
import kotlinx.coroutines.flow.Flow
import timber.log.Timber
import java.io.IOException

class DataStoreRepo(context: Context) {

    private val dataStore: DataStore = DataStore(context = context)

    fun getUserData(): Flow<UserData> {
        return dataStore.getUserData
    }

    suspend fun setDateWhenQuitInMillis(date: Long) {
        try {
            dataStore.setDateWhenQuitInMillis(quitDate = date)
            Timber.d("Successfully stored date: $date")
        } catch (e: IOException) {
            emptyPreferences()
            Timber.e(e)
            Timber.d("Failed storing date")
        }
    }

    suspend fun setAmountOfUnits(units: Int) {
        try {
            dataStore.setAmountOfUnits(units)
            Timber.d("Successfully stored amount of units: $units")
        } catch (e: IOException) {
            Timber.e(e)
            Timber.d("Failed storing amount of units")
        }
    }

    suspend fun setCostPerUnit(cost: Int) {
        try {
            dataStore.setCost(cost = cost)
            Timber.d("Successfully stored cost: $cost")
        } catch (e: IOException) {
            Timber.e(e)
            Timber.d("Failed storing cost")
        }
    }
}
