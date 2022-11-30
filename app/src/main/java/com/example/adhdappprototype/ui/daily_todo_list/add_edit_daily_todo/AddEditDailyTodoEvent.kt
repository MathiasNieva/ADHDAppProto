package com.example.adhdappprototype.ui.daily_todo_list.add_edit_daily_todo

import com.example.adhdappprototype.ui.add_edit_comprehensive_todo.AddEditComprehensiveTodoEvent

sealed class AddEditDailyTodoEvent {
    data class OnTitleChange(val title: String): AddEditDailyTodoEvent()
    data class OnDescriptionChange(val description: String): AddEditDailyTodoEvent()
    object OnSaveTodoClick: AddEditDailyTodoEvent()
}
