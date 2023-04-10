package com.antisnusbolaget.slutasnusa2.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.antisnusbolaget.slutasnusa2.data.entity.USER_SETTINGS_TABLE
import com.antisnusbolaget.slutasnusa2.data.entity.UserSettingsEntity

@Dao
interface UserSettingsDao {
    @Query(value = "SELECT * FROM $USER_SETTINGS_TABLE")
    suspend fun getAllUserSettings(): UserSettingsEntity

    @Query(value = "SELECT costPerUnit FROM $USER_SETTINGS_TABLE")
    suspend fun getCostPerUnit(): Int?

    @Query(value = "SELECT unitAmount FROM $USER_SETTINGS_TABLE")
    suspend fun getUnitAmount(): Int?

    @Query(value = "SELECT quitDate FROM $USER_SETTINGS_TABLE")
    suspend fun getQuitDate(): Long?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertUserSettings(userSettingsEntity: UserSettingsEntity)
}
