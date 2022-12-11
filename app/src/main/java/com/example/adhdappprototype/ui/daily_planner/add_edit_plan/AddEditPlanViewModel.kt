package com.example.adhdappprototype.ui.daily_planner.add_edit_plan

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adhdappprototype.data.ComprehensiveTodo
import com.example.adhdappprototype.data.Repository
import com.example.adhdappprototype.data.daily_planner.DailyPlan
import com.example.adhdappprototype.data.daily_planner.DailyPlanRepository
import com.example.adhdappprototype.data.util.Priority
import com.example.adhdappprototype.data.util.Tag
import com.example.adhdappprototype.ui.add_edit_comprehensive_todo.AddEditComprehensiveTodoEvent
import com.example.adhdappprototype.ui.daily_todo_list.add_edit_daily_todo.AddEditDailyTodoEvent
import com.example.adhdappprototype.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditPlanViewModel @Inject constructor(
    private val repository: DailyPlanRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    var todo by mutableStateOf<DailyPlan?>(null)
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

    var time by mutableStateOf("")
        private set

    private val _uiEvent =  Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        val todoId = savedStateHandle.get<Int>("todoId")!!
        if(todoId != -1) {
            viewModelScope.launch {
                repository.getPlanById(todoId)?.let { todo ->
                    title = todo.title
                    description = todo.description ?: ""
                    tag = todo.tag
                    priority = todo.priority
                    ifThenPlan = todo.ifThenPlan ?: ""
                    time = todo.time ?: ""
                    this@AddEditPlanViewModel.todo = todo
                }
            }
        }
    }

    fun onEvent(event: AddEditPlanEvent) {
        when(event) {
            is AddEditPlanEvent.OnTitleChange -> {
                title = event.title
            }
            is AddEditPlanEvent.OnDescriptionChange -> {
                description = event.description
            }
            is AddEditPlanEvent.OnTagChange -> {
                tag = event.tag
            }
            is AddEditPlanEvent.OnPriorityChange -> {
                priority = event.priority
            }
            is AddEditPlanEvent.OnIfThenPlanChange -> {
                ifThenPlan = event.ifThenPlan
            }
            is AddEditPlanEvent.OnTimeChange -> {
                time = event.time
            }
            is AddEditPlanEvent.OnSaveTodoClick -> {
                viewModelScope.launch {
                    if(title.isBlank()) {
                        sendUiEvent(
                            UiEvent.ShowSnackbar(
                            message = "The title can't be empty"
                        ))
                        return@launch
                    }
                    if(time.isBlank()) {
                        sendUiEvent(
                            UiEvent.ShowSnackbar(
                                message = "Must have time frame"
                            ))
                        return@launch
                    }
                    repository.insertPlan(
                        DailyPlan(
                            title = title,
                            description = description,
                            tag = tag,
                            priority = priority,
                            ifThenPlan = ifThenPlan,
                            time = time,
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