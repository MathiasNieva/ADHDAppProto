package com.example.adhdappprototype.ui.daily_todo_list.add_edit_daily_todo

import com.example.adhdappprototype.data.util.Priority
import com.example.adhdappprototype.data.util.Tag
import com.example.adhdappprototype.ui.add_edit_comprehensive_todo.AddEditComprehensiveTodoEvent

sealed class AddEditDailyTodoEvent {
    data class OnTitleChange(val title: String): AddEditDailyTodoEvent()
    data class OnDescriptionChange(val description: String): AddEditDailyTodoEvent()
    data class OnTagChange(val tag: Tag): AddEditDailyTodoEvent()
    data class OnPriorityChange(val priority: Priority): AddEditDailyTodoEvent()
    data class OnIfThenPlanChange(val ifThenPlan: String): AddEditDailyTodoEvent()
    data class OnTimeFrameChange(val timeFrame: String): AddEditDailyTodoEvent()
    object OnSaveTodoClick: AddEditDailyTodoEvent()
}
