package com.example.adhdappprototype.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adhdappprototype.data.settings.SettingsRepository
import com.example.adhdappprototype.util.Routes
import com.example.adhdappprototype.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val repository: SettingsRepository
): ViewModel() {

    val settings = repository.getSettings()

    private val _uiEvent =  Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: SettingsEvent) {
        when(event) {
            is SettingsEvent.OnSettingClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.EDIT_SETTINGS))
            }
            is SettingsEvent.OnAddSettingClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.EDIT_SETTINGS))
            }
            is SettingsEvent.OnDoneChange -> {
                viewModelScope.launch {
                    repository.insertSetting(
                        event.settings
                    )
                }
            }
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}