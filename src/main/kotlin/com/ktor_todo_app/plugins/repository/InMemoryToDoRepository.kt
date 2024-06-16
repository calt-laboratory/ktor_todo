package com.ktor_todo_app.plugins.repository

import com.ktor_todo_app.plugins.entities.ToDo


class InMemoryToDoRepository : ToDoRepositoryPattern {

    private val todos = listOf(
        ToDo(id = 1, title = "learn Python", done = true),
        ToDo(id = 2, title = "learn Kotlin", done = true),
        ToDo(id = 3, title = "learn ktor", done = false),
    )

    override fun getAllToDos() : List<ToDo> {
        return todos
    }

    override fun getSingleToDo(id: Int) : ToDo? {
        return todos.firstOrNull { it.id == id }
    }
}