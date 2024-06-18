package com.ktor_todo_app.plugins.repository

import com.ktor_todo_app.plugins.entities.ToDo
import com.ktor_todo_app.plugins.entities.ToDoDraft


class InMemoryToDoRepository : ToDoRepositoryPattern {

    private val todos = mutableListOf(
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

    override fun addToDo(toDoDraft: ToDoDraft): ToDo {
        val todo = ToDo(
            id = todos.size + 1,
            title = toDoDraft.title,
            done = toDoDraft.done,
        )
        todos.add(todo)
        return todo
    }

    override fun updateToDo(id: Int, toDoDraft: ToDoDraft): Boolean {
        val todo = todos.firstOrNull { it.id == id } ?: return false
        todo.title = toDoDraft.title
        todo.done = toDoDraft.done
        return true
    }

    override fun deleteToDo(id: Int): Boolean {
        return todos.removeIf { it.id == id }
    }
}
