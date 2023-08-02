package com.antisnusbolaget.slutasnusa2.data

import android.content.Context
import com.antisnusbolaget.slutasnusa2.viewmodel.`interface`.UserData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import java.io.IOException

class DataStoreRepo(context: Context) {

    private val dataStore: DataStore = DataStore(context = context)

    fun getUserData(): Flow<UserData> {
        return dataStore.getUserData.map {
            UserData(
                costPerUnit = it.costPerUnit.toInt(),
                units = it.units.toInt(),
                dateWhenQuit = it.dateWhenQuit.toLong(),
            )
        }
    }

    suspend fun setDateWhenQuitInMillis(date: String) {
        try {
            dataStore.setDateWhenQuitInMillis(quitDate = date)
            Timber.d("Successfully stored date")
        } catch (e: IOException) {
            Timber.e(e)
            Timber.d("Failed storing date")
        }
    }

    suspend fun setAmountOfUnits(units: String) {
        try {
            dataStore.setAmountOfUnits(units)
            Timber.d("Successfully stored amount of units")
        } catch (e: IOException) {
            Timber.e(e)
            Timber.d("Failed storing amount of units")
        }
    }

    suspend fun setCostPerUnit(cost: String) {
        try {
            dataStore.setCost(cost = cost)
            Timber.d("Successfully stored cost")
        } catch (e: IOException) {
            Timber.e(e)
            Timber.d("Failed storing cost")
        }
    }
}
