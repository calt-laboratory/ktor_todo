package com.ktor_todo_app.plugins.repository

import com.ktor_todo_app.plugins.database.DatabaseManager
import com.ktor_todo_app.plugins.entities.ToDo
import com.ktor_todo_app.plugins.entities.ToDoDraft

class PostgresToDoRepository : ToDoRepositoryPattern {

    private val database = DatabaseManager()

    override fun getAllToDos(): List<ToDo> {
        return database.getAllTodos()
            .map { ToDo(it.id.value, it.title, it.done) }
    }

    override fun getSingleToDo(id: Int): ToDo? {
        TODO("Not yet implemented")
    }

    override fun addToDo(draft: ToDoDraft): ToDo {
        TODO("Not yet implemented")
    }

    override fun deleteToDo(id: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun updateToDo(id: Int, draft: ToDoDraft): Boolean {
        TODO("Not yet implemented")
    }

}