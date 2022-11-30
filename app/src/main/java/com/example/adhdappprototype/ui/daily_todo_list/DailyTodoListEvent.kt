package com.example.adhdappprototype.ui.daily_todo_list

import com.example.adhdappprototype.data.ComprehensiveTodo
import com.example.adhdappprototype.data.daily_todo.DailyTodo
import com.example.adhdappprototype.ui.comprehensive_todo_list.ComprehensiveTodoListEvent

sealed class DailyTodoListEvent {
    data class OnDeleteTodoClick(val todo: DailyTodo): DailyTodoListEvent()
    data class OnDoneChange(val todo: DailyTodo, val isDone: Boolean): DailyTodoListEvent()
    object OnUndoDeleteClick: DailyTodoListEvent()
    data class OnTodoClick(val todo: DailyTodo): DailyTodoListEvent()
    object OnAddTodoClick: DailyTodoListEvent()
}
