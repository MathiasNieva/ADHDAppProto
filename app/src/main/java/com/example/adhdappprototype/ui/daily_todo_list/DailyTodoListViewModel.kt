package com.example.adhdappprototype.ui.daily_todo_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adhdappprototype.data.ComprehensiveTodo
import com.example.adhdappprototype.data.Repository
import com.example.adhdappprototype.data.daily_todo.DailyTodo
import com.example.adhdappprototype.data.daily_todo.DailyTodoRepository
import com.example.adhdappprototype.ui.comprehensive_todo_list.ComprehensiveTodoListEvent
import com.example.adhdappprototype.util.Routes
import com.example.adhdappprototype.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DailyTodoListViewModel @Inject constructor(
    private val repository: DailyTodoRepository
): ViewModel() {

    val todos = repository.getDailyTodos()

    private val _uiEvent =  Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private var deletedTodo: DailyTodo? = null

    fun onEvent(event: DailyTodoListEvent) {
        when(event) {
            is DailyTodoListEvent.OnTodoClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.ADD_EDIT_DAILY_TODO + "?todoId=${event.todo.id}"))
            }
            is DailyTodoListEvent.OnAddTodoClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.ADD_EDIT_DAILY_TODO))
            }
            is DailyTodoListEvent.OnUndoDeleteClick -> {
                deletedTodo?.let { todo ->
                    viewModelScope.launch {
                        repository.insertDailyTodo(todo)
                    }
                }
            }
            is DailyTodoListEvent.OnDeleteTodoClick -> {
                viewModelScope.launch {
                    deletedTodo = event.todo
                    repository.deleteDailyTodo(event.todo)
                    sendUiEvent(
                        UiEvent.ShowSnackbar(
                        message = "Todo deleted",
                        action = "Undo"
                    ))
                }
            }
            is DailyTodoListEvent.OnDoneChange -> {
                viewModelScope.launch {
                    repository.insertDailyTodo(
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