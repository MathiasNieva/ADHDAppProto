package com.example.adhdappprototype.data.daily_planner

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DailyPlan(
    val title: String,
    val description: String?,
    val isDone: Boolean,
    @PrimaryKey val id: Int? = null
)
