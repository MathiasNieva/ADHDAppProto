package com.example.adhdappprototype.data

import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun insertTodo(todo: ComprehensiveTodo)

    suspend fun deleteTodo(todo: ComprehensiveTodo)

    suspend fun getTodoById(id: Int): ComprehensiveTodo?

    fun getTodos(): Flow<List<ComprehensiveTodo>>

}