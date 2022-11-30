package com.example.adhdappprototype.data.daily_todo

import kotlinx.coroutines.flow.Flow

class DailyTodoRepositoryImpl (
    private val daily_todo_dao: DailyTodoDao
): DailyTodoRepository {

    override suspend fun insertDailyTodo(todo: DailyTodo) {
        daily_todo_dao.insertDailyTodo(todo)
    }

    override suspend fun deleteDailyTodo(todo: DailyTodo) {
        daily_todo_dao.deleteDailyTodo(todo)
    }

    override suspend fun getDailyTodoById(id: Int): DailyTodo? {
        return daily_todo_dao.getDailyTodoById(id)
    }

    override fun getDailyTodos(): Flow<List<DailyTodo>> {
        return daily_todo_dao.getDailyTodos()
    }
}