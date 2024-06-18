package com.ktor_todo_app.plugins.repository

import com.ktor_todo_app.plugins.entities.ToDo
import com.ktor_todo_app.plugins.entities.ToDoDraft

interface ToDoRepositoryPattern {
    fun getAllToDos() : List<ToDo>

    fun getSingleToDo(id: Int) : ToDo?

    fun addToDo(toDoDraft: ToDoDraft) : ToDo

    fun deleteToDo(id: Int) : Boolean

    fun updateToDo(id: Int, toDoDraft: ToDoDraft) : Boolean
}
