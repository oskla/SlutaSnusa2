package com.antisnusbolaget.slutasnusa2.repository

import android.util.Log
import com.antisnusbolaget.slutasnusa2.data.entity.UserSettingsEntity
import com.antisnusbolaget.slutasnusa2.data.room.UserSettingsDao

class UserSettingsRepository(private val userSettingsDao: UserSettingsDao) {

    suspend fun getUserSettings(): UserSettingsEntity {
        Log.d("Repository", "Getting: ${userSettingsDao.getAllUserSettings()}")
        return userSettingsDao.getAllUserSettings()
    }

    suspend fun setCost() {
        userSettingsDao.upsertUserSettings(UserSettingsEntity(costPerUnit = 64, unitAmount = 4, quitDate = 2))
        Log.d("Repository", "Succeeded)")
    }

    suspend fun getCost() {
        val cost = userSettingsDao.getCostPerUnit()
        Log.d("Repository", "Cost: $cost")
    }

    suspend fun updateUserSettings(cost: Int? = null, unit: Int? = null, date: Long? = null) {
        val costDao = userSettingsDao.getCostPerUnit()
        val unitDao = userSettingsDao.getUnitAmount()
        val dateDao = userSettingsDao.getQuitDate()

        val userSettingsEntity = UserSettingsEntity(
            id = 1,
            costPerUnit = cost ?: costDao,
            unitAmount = unit ?: unitDao,
            quitDate = date ?: dateDao,
        )
        Log.d("Repository", "UserSettingsEntity updated: $userSettingsEntity")
        return userSettingsDao.upsertUserSettings(userSettingsEntity)
    }
}
