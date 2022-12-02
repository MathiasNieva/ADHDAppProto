package com.example.adhdappprototype.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.adhdappprototype.data.util.Priority
import com.example.adhdappprototype.data.util.Tag


@Entity
data class ComprehensiveTodo(
    val title: String,
    val description: String?,
    val isDone: Boolean,
    val tag: Tag?,
    val priority: Priority?,
    @PrimaryKey val id: Int? = null
)
