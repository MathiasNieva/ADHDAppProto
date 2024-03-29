package com.example.adhdappprototype.ui.add_edit_comprehensive_todo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adhdappprototype.data.ComprehensiveTodo
import com.example.adhdappprototype.data.Repository
import com.example.adhdappprototype.data.util.Priority
import com.example.adhdappprototype.data.util.Tag
import com.example.adhdappprototype.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditComprehensiveTodoViewModel @Inject constructor(
    private val repository: Repository,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    var todo by mutableStateOf<ComprehensiveTodo?>(null)
        private set

    var title by mutableStateOf("")
        private set

    var description by mutableStateOf("")
        private set

    var tag by mutableStateOf<Tag?>(null)
        private set

    var priority by mutableStateOf<Priority?>(null)
        private set

    var reminderDate by mutableStateOf("")
        private set

    var reminderTime by mutableStateOf("")
        private set

    private val _uiEvent =  Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        val todoId = savedStateHandle.get<Int>("todoId")!!
        if(todoId != -1) {
            viewModelScope.launch {
                repository.getTodoById(todoId)?.let { todo ->
                    title = todo.title
                    description = todo.description ?: ""
                    tag = todo.tag
                    priority = todo.priority
                    reminderDate = todo.reminderDate ?: ""
                    reminderTime = todo.reminderTime ?: ""
                    this@AddEditComprehensiveTodoViewModel.todo = todo
                }
            }
        }
    }

    fun onEvent(event: AddEditComprehensiveTodoEvent) {
        when(event) {
            is AddEditComprehensiveTodoEvent.OnTitleChange -> {
                title = event.title
            }
            is AddEditComprehensiveTodoEvent.OnDescriptionChange -> {
                description = event.description
            }
            is AddEditComprehensiveTodoEvent.OnTagChange -> {
                tag = event.tag
            }
            is AddEditComprehensiveTodoEvent.OnPriorityChange -> {
                priority = event.priority
            }
            is AddEditComprehensiveTodoEvent.OnReminderDate -> {
                reminderDate = event.reminderDate
            }
            is AddEditComprehensiveTodoEvent.OnReminderTime -> {
                reminderTime = event.reminderTime
            }
            is AddEditComprehensiveTodoEvent.OnSaveTodoClick -> {
                viewModelScope.launch {
                    if(title.isBlank()) {
                        sendUiEvent(UiEvent.ShowSnackbar(
                            message = "The title can't be empty"
                        ))
                        return@launch
                    }
                    repository.insertTodo(
                        ComprehensiveTodo(
                            title = title,
                            description = description,
                            tag = tag,
                            priority = priority,
                            reminderDate = reminderDate,
                            reminderTime = reminderTime,
                            isDone = todo?.isDone ?: false,
                            id = todo?.id
                        )
                    )
                    sendUiEvent(UiEvent.PopBackStack)
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