package com.example.adhdappprototype.data.daily_todo

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface DailyTodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDailyTodo(todo: DailyTodo)

    @Delete
    suspend fun deleteDailyTodo(todo: DailyTodo)

    @Query("SELECT * FROM DailyTodo WHERE id = :id")
    suspend fun getDailyTodoById(id: Int): DailyTodo?

    @Query("SELECT * FROM DailyTodo")
    fun getDailyTodos(): Flow<List<DailyTodo>>
}