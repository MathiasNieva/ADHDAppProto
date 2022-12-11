package com.example.adhdappprototype.data.daily_planner

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.adhdappprototype.data.util.Priority
import com.example.adhdappprototype.data.util.Tag

@Entity
data class DailyPlan(
    val title: String,
    val description: String?,
    val time: String,
    val isDone: Boolean,
    val tag: Tag?,
    val priority: Priority?,
    val ifThenPlan: String?,
    @PrimaryKey val id: Int? = null
)
