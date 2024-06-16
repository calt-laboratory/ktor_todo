package com.ktor_todo_app.plugins.repository

import com.ktor_todo_app.plugins.entities.ToDo

interface ToDoRepositoryPattern {
    fun getAllToDos() : List<ToDo>

    fun getSingleToDo(id: Int) : ToDo?
}