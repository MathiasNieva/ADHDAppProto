package com.example.adhdappprototype.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.adhdappprototype.data.daily_planner.DailyPlan
import com.example.adhdappprototype.data.daily_planner.DailyPlanDao
import com.example.adhdappprototype.data.daily_todo.DailyTodo
import com.example.adhdappprototype.data.daily_todo.DailyTodoDao

@Database(
    entities = [ComprehensiveTodo::class, DailyTodo::class, DailyPlan::class],
    version = 1
)
abstract class Database: RoomDatabase() {

    abstract val dao: ComprehensiveTodoDao
    abstract val dailyTodoDao: DailyTodoDao
    abstract val dailyPlanDao: DailyPlanDao
}