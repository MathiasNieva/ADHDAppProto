package com.example.adhdappprototype.ui.settings

import com.example.adhdappprototype.data.settings.Settings

sealed class SettingsEvent {
    data class OnDoneChange(val settings: Settings): SettingsEvent()
    object OnSettingClick: SettingsEvent()
    object OnAddSettingClick: SettingsEvent()
}
