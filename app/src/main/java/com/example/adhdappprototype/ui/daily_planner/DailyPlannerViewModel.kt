package com.example.adhdappprototype.ui.daily_planner

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adhdappprototype.data.ComprehensiveTodo
import com.example.adhdappprototype.data.Repository
import com.example.adhdappprototype.data.daily_planner.DailyPlan
import com.example.adhdappprototype.data.daily_planner.DailyPlanRepository
import com.example.adhdappprototype.ui.comprehensive_todo_list.ComprehensiveTodoListEvent
import com.example.adhdappprototype.util.Routes
import com.example.adhdappprototype.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DailyPlannerViewModel @Inject constructor(
    private val repository: DailyPlanRepository
): ViewModel() {

    val todos = repository.getPlans()

    private val _uiEvent =  Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private var deletedTodo: DailyPlan? = null

    fun onEvent(event: DailyPlannerEvent) {
        when(event) {
            is DailyPlannerEvent.OnTodoClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.ADD_EDIT_DAILY_PLAN + "?todoId=${event.todo.id}"))
            }
            is DailyPlannerEvent.OnAddTodoClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.ADD_EDIT_DAILY_PLAN))
            }
            is DailyPlannerEvent.OnUndoDeleteClick -> {
                deletedTodo?.let { todo ->
                    viewModelScope.launch {
                        repository.insertPlan(todo)
                    }
                }
            }
            is DailyPlannerEvent.OnDeleteTodoClick -> {
                viewModelScope.launch {
                    deletedTodo = event.todo
                    repository.deletePlan(event.todo)
                    sendUiEvent(
                        UiEvent.ShowSnackbar(
                        message = "Todo deleted",
                        action = "Undo"
                    ))
                }
            }
            is DailyPlannerEvent.OnDoneChange -> {
                viewModelScope.launch {
                    repository.insertPlan(
                        event.todo.copy(
                            isDone = event.isDone
                        )
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