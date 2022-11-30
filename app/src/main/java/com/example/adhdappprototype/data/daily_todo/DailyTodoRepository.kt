package com.example.adhdappprototype.data.daily_todo

import kotlinx.coroutines.flow.Flow

interface DailyTodoRepository {

    suspend fun insertDailyTodo(todo: DailyTodo)

    suspend fun deleteDailyTodo(todo: DailyTodo)

    suspend fun getDailyTodoById(id: Int): DailyTodo?

    fun getDailyTodos(): Flow<List<DailyTodo>>
}