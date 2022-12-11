package com.example.adhdappprototype.ui.daily_planner.add_edit_plan

import com.example.adhdappprototype.data.util.Priority
import com.example.adhdappprototype.data.util.Tag

sealed class AddEditPlanEvent {
    data class OnTitleChange(val title: String): AddEditPlanEvent()
    data class OnDescriptionChange(val description: String): AddEditPlanEvent()
    data class OnTagChange(val tag: Tag): AddEditPlanEvent()
    data class OnPriorityChange(val priority: Priority): AddEditPlanEvent()
    data class OnIfThenPlanChange(val ifThenPlan: String): AddEditPlanEvent()
    data class OnTimeChange(val time: String): AddEditPlanEvent()
    object OnSaveTodoClick: AddEditPlanEvent()
}
