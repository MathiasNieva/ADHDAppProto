package com.example.adhdappprototype.ui.daily_todo_list.add_edit_daily_todo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adhdappprototype.data.ComprehensiveTodo
import com.example.adhdappprototype.data.Repository
import com.example.adhdappprototype.data.daily_todo.DailyTodo
import com.example.adhdappprototype.data.daily_todo.DailyTodoRepository
import com.example.adhdappprototype.data.util.Priority
import com.example.adhdappprototype.data.util.Tag
import com.example.adhdappprototype.ui.add_edit_comprehensive_todo.AddEditComprehensiveTodoEvent
import com.example.adhdappprototype.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditDailyTodoViewModel @Inject constructor(
    private val repository: DailyTodoRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    var todo by mutableStateOf<DailyTodo?>(null)
        private set

    var title by mutableStateOf("")
        private set

    var description by mutableStateOf("")
        private set

    var tag by mutableStateOf<Tag?>(null)
        private set

    var priority by mutableStateOf<Priority?>(null)
        private set

    var ifThenPlan by mutableStateOf("")
        private set

    var timeFrame by mutableStateOf("")
        private set

    private val _uiEvent =  Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        val todoId = savedStateHandle.get<Int>("todoId")!!
        if(todoId != -1) {
            viewModelScope.launch {
                repository.getDailyTodoById(todoId)?.let { todo ->
                    title = todo.title
                    description = todo.description ?: ""
                    tag = todo.tag
                    priority = todo.priority
                    ifThenPlan = todo.ifThenPlan ?: ""
                    timeFrame = todo.timeFrame ?: ""
                    this@AddEditDailyTodoViewModel.todo = todo
                }
            }
        }
    }

    fun onEvent(event: AddEditDailyTodoEvent) {
        when(event) {
            is AddEditDailyTodoEvent.OnTitleChange -> {
                title = event.title
            }
            is AddEditDailyTodoEvent.OnDescriptionChange -> {
                description = event.description
            }
            is AddEditDailyTodoEvent.OnTagChange -> {
                tag = event.tag
            }
            is AddEditDailyTodoEvent.OnPriorityChange -> {
                priority = event.priority
            }
            is AddEditDailyTodoEvent.OnIfThenPlanChange -> {
                ifThenPlan = event.ifThenPlan
            }
            is AddEditDailyTodoEvent.OnTimeFrameChange -> {
                timeFrame = event.timeFrame
            }
            is AddEditDailyTodoEvent.OnSaveTodoClick -> {
                viewModelScope.launch {
                    if(title.isBlank()) {
                        sendUiEvent(
                            UiEvent.ShowSnackbar(
                            message = "The title can't be empty"
                        ))
                        return@launch
                    }
                    repository.insertDailyTodo(
                        DailyTodo(
                            title = title,
                            description = description,
                            tag = tag,
                            priority = priority,
                            ifThenPlan = ifThenPlan,
                            timeFrame = timeFrame,
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