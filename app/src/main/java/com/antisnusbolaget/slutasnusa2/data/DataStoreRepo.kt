package com.antisnusbolaget.slutasnusa2.data

import android.content.Context
import kotlinx.coroutines.flow.Flow
import timber.log.Timber
import java.io.IOException

class DataStoreRepo(context: Context) {

    private val dataStore: DataStore = DataStore(context = context)

    fun getDateWhenQuit(): Flow<String> {
        return dataStore.getQuitDate
    }

    fun getCostPerUnit(): Flow<String> {
        return dataStore.getCost
    }

    fun getAmountOfUnits(): Flow<String> {
        return dataStore.getUnits
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
