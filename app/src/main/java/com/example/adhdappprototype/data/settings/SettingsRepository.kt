package com.example.adhdappprototype.data.settings

import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    suspend fun insertSetting(settings: Settings)

    suspend fun deleteSetting(settings: Settings)

    suspend fun getSettingById(id: Int): Settings?

    fun getSettings(): Flow<List<Settings>>

}