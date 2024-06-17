package com.ktor_todo_app.plugins.utils

import com.ktor_todo_app.plugins.repository.ToDoRepositoryPattern
import io.ktor.server.html.*
import io.ktor.server.application.*
import kotlinx.html.*


suspend fun ApplicationCall.respondTodos(repository: ToDoRepositoryPattern) {
    val todos = repository.getAllToDos()
    respondHtml {
        head {
            title("Todo List")
            script(src = "https://unpkg.com/htmx.org@1.6.1/dist/htmx.min.js") {}
        }
        body {
            h1 { +"Todo List" }
            ul {
                todos.forEach { todo ->
                    li {
                        +"${todo.title} - ${if (todo.done) "Done" else "Not Done"}"
                    }
                }
            }
            button {
                attributes["hx-get"] = "/todos"
                attributes["hx-trigger"] = "click"
                attributes["hx-target"] = "#todoList"
                +"Reload Todos"
            }
        }
    }
}

