package com.example.adhdappprototype.ui.add_edit_comprehensive_todo

import com.example.adhdappprototype.data.util.Priority
import com.example.adhdappprototype.data.util.Tag

sealed class AddEditComprehensiveTodoEvent {
    data class OnTitleChange(val title: String): AddEditComprehensiveTodoEvent()
    data class OnDescriptionChange(val description: String): AddEditComprehensiveTodoEvent()
    data class OnTagChange(val tag: Tag): AddEditComprehensiveTodoEvent()
    data class OnPriorityChange(val priority: Priority): AddEditComprehensiveTodoEvent()
    object OnSaveTodoClick: AddEditComprehensiveTodoEvent()
}
