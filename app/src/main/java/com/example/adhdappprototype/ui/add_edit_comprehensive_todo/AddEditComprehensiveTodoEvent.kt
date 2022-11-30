package com.example.adhdappprototype.ui.add_edit_comprehensive_todo

sealed class AddEditComprehensiveTodoEvent {
    data class OnTitleChange(val title: String): AddEditComprehensiveTodoEvent()
    data class OnDescriptionChange(val description: String): AddEditComprehensiveTodoEvent()
    object OnSaveTodoClick: AddEditComprehensiveTodoEvent()
}
