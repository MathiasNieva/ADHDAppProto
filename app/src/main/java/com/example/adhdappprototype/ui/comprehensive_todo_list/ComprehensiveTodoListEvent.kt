package com.example.adhdappprototype.ui.comprehensive_todo_list

import com.example.adhdappprototype.data.ComprehensiveTodo

sealed class ComprehensiveTodoListEvent {
    data class OnDeleteTodoClick(val todo: ComprehensiveTodo): ComprehensiveTodoListEvent()
    data class OnDoneChange(val todo: ComprehensiveTodo, val isDone: Boolean): ComprehensiveTodoListEvent()
    object OnUndoDeleteClick: ComprehensiveTodoListEvent()
    data class OnTodoClick(val todo: ComprehensiveTodo): ComprehensiveTodoListEvent()
    object OnAddTodoClick: ComprehensiveTodoListEvent()
}
