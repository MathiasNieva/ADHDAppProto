package com.example.adhdappprototype.data.settings

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface SettingsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSetting(settings: Settings)

    @Delete
    suspend fun deleteSetting(settings: Settings)

    @Query("SELECT * FROM Settings WHERE id = :id")
    suspend fun getSettingById(id: Int): Settings?

    @Query("SELECT * FROM Settings")
    fun getSettings(): Flow<List<Settings>>
}