package com.ktor_todo_app.plugins

import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.callloging.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.plugins.contentnegotiation.*
import kotlinx.serialization.json.Json

fun Application.configureRouting(routing: Routing) {

    install(CallLogging)
    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            isLenient = true
        })
    }

    routing {

        val repository: ToDoRepositoryPattern = InMemoryToDoRepository()

        get(path = "/") {
            call.respondText("Todo List")
        }

        get(path = "/todos") {
            call.respond(repository.getAllToDos())
        }

        get(path = "/todos/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()
            if (id == null) {
                call.respond(HttpStatusCode.BadRequest, message = "ID must be an integer")
                return@get
            }

            val todo = repository.getSingleToDo(id = id)
            if (todo == null) {
                call.respond(HttpStatusCode.NotFound, message = "Todo not found")
            } else {
                call.respond(todo)
            }
        }

        post(path = "/todos") {

        }

        put(path = "/todos/{id}") {

        }

        delete(path = "/todos/{id}") {

        }
    }
}
