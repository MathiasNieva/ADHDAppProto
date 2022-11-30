package com.example.adhdappprototype.ui.daily_planner

import com.example.adhdappprototype.data.ComprehensiveTodo
import com.example.adhdappprototype.data.daily_planner.DailyPlan
import com.example.adhdappprototype.ui.comprehensive_todo_list.ComprehensiveTodoListEvent

sealed class DailyPlannerEvent {
    data class OnDeleteTodoClick(val todo: DailyPlan): DailyPlannerEvent()
    data class OnDoneChange(val todo: DailyPlan, val isDone: Boolean): DailyPlannerEvent()
    object OnUndoDeleteClick: DailyPlannerEvent()
    data class OnTodoClick(val todo: DailyPlan): DailyPlannerEvent()
    object OnAddTodoClick: DailyPlannerEvent()
}
