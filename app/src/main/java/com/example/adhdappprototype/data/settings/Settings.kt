package com.example.adhdappprototype.data.settings

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Settings (
    val timeToPlan: String,
    @PrimaryKey val id: Int? = null
)
