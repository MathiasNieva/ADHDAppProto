package com.example.adhdappprototype.data.daily_todo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DailyTodo(
    val title: String,
    val description: String?,
    val isDone: Boolean,
    @PrimaryKey val id: Int? = null
)
