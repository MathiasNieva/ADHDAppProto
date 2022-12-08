package com.example.adhdappprototype.data.daily_todo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.adhdappprototype.data.util.Priority
import com.example.adhdappprototype.data.util.Tag

@Entity
data class DailyTodo(
    val title: String,
    val description: String?,
    val isDone: Boolean,
    val tag: Tag?,
    val priority: Priority?,
    val ifThenPlan: String?,
    val timeFrame: String?,
    @PrimaryKey val id: Int? = null
)
