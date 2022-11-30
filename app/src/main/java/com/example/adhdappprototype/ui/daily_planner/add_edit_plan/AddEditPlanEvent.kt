package com.example.adhdappprototype.ui.daily_planner.add_edit_plan

import com.example.adhdappprototype.ui.add_edit_comprehensive_todo.AddEditComprehensiveTodoEvent

sealed class AddEditPlanEvent {
    data class OnTitleChange(val title: String): AddEditPlanEvent()
    data class OnDescriptionChange(val description: String): AddEditPlanEvent()
    object OnSaveTodoClick: AddEditPlanEvent()
}
