package com.example.adhdappprototype.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ComprehensiveTodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(todo: ComprehensiveTodo)

    @Delete
    suspend fun deleteTodo(todo: ComprehensiveTodo)

    @Query("SELECT * FROM ComprehensiveTodo WHERE id = :id")
    suspend fun getTodoById(id: Int): ComprehensiveTodo?

    @Query("SELECT * FROM ComprehensiveTodo")
    fun getTodos(): Flow<List<ComprehensiveTodo>>
}