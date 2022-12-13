package com.example.adhdappprototype.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.adhdappprototype.data.daily_planner.DailyPlan
import com.example.adhdappprototype.data.daily_planner.DailyPlanDao
import com.example.adhdappprototype.data.daily_todo.DailyTodo
import com.example.adhdappprototype.data.daily_todo.DailyTodoDao
import com.example.adhdappprototype.data.settings.Settings
import com.example.adhdappprototype.data.settings.SettingsDao

@Database(
    entities = [ComprehensiveTodo::class, DailyTodo::class, DailyPlan::class, Settings::class],
    version = 1
)
abstract class Database: RoomDatabase() {

    abstract val dao: ComprehensiveTodoDao
    abstract val dailyTodoDao: DailyTodoDao
    abstract val dailyPlanDao: DailyPlanDao
    abstract val settingsDao: SettingsDao
}