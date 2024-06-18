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
            val toDoEntity = database.getSingleToDo(id)
            return toDoEntity?.let { ToDo(it.id.value, it.title, it.done) }
    }

    override fun addToDo(toDoDraft: ToDoDraft): ToDo {
        val toDoEntity = database.addToDo(toDoDraft = toDoDraft)
        return ToDo(toDoEntity.id.value, toDoEntity.title, toDoEntity.done)
    }

    override fun updateToDo(id: Int, toDoDraft: ToDoDraft): Boolean {
        return database.updateToDo(id = id, toDoDraft = toDoDraft)
    }

    override fun deleteToDo(id: Int): Boolean {
        return database.deleteToDo(id = id)
    }
}
