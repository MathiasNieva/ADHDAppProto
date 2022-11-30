package com.example.adhdappprototype.data

import kotlinx.coroutines.flow.Flow

class ComprehensiveTodoRepImpl (
    private val dao: ComprehensiveTodoDao
): Repository {

    override suspend fun insertTodo(todo: ComprehensiveTodo) {
        dao.insertTodo(todo)
    }

    override suspend fun deleteTodo(todo: ComprehensiveTodo) {
        dao.deleteTodo(todo)
    }

    override suspend fun getTodoById(id: Int): ComprehensiveTodo? {
        return dao.getTodoById(id)
    }

    override fun getTodos(): Flow<List<ComprehensiveTodo>> {
        return dao.getTodos()
    }
}