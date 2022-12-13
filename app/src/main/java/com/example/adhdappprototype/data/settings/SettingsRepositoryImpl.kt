package com.example.adhdappprototype.data.settings

import kotlinx.coroutines.flow.Flow

class SettingsRepositoryImpl (
    private val settingsDao: SettingsDao
): SettingsRepository {

    override suspend fun insertSetting(settings: Settings) {
        settingsDao.insertSetting(settings)
    }

    override suspend fun deleteSetting(settings: Settings) {
        settingsDao.deleteSetting(settings)
    }

    override suspend fun getSettingById(id: Int): Settings? {
        return settingsDao.getSettingById(id)
    }

    override fun getSettings(): Flow<List<Settings>> {
        return settingsDao.getSettings()
    }
}