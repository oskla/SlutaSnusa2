package com.antisnusbolaget.slutasnusa2.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.antisnusbolaget.slutasnusa2.data.entity.UserSettingsEntity
import kotlinx.coroutines.InternalCoroutinesApi

@Database(entities = [UserSettingsEntity::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userSettingsDao(): UserSettingsDao

    companion object {
        private var INSTANCE: UserDatabase? = null

        @OptIn(InternalCoroutinesApi::class)
        fun getInstance(context: Context): UserDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        UserDatabase::class.java,
                        "user_database",
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
