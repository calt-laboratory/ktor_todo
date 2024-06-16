package com.ktor_todo_app.plugins

interface ToDoRepositoryPattern {
    fun getAllToDos() : List<ToDo>

    fun getSingleToDo(id: Int) : ToDo?
}