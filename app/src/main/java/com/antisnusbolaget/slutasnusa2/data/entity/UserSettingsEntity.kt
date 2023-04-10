package com.antisnusbolaget.slutasnusa2.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

const val USER_SETTINGS_TABLE = "USER_SETTINGS_TABLE"

@Entity(tableName = USER_SETTINGS_TABLE)
data class UserSettingsEntity(
    @PrimaryKey
    @ColumnInfo
    val id: Int = 1,

    @ColumnInfo(name = "costPerUnit")
    val costPerUnit: Int = 0,

    @ColumnInfo(name = "unitAmount")
    val unitAmount: Int = 0,

    @ColumnInfo(name = "quitDate")
    val quitDate: Long = 0,
)
