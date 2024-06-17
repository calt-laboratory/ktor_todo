package com.ktor_todo_app.plugins.routes

import com.ktor_todo_app.plugins.entities.ToDoDraft
import com.ktor_todo_app.plugins.repository.InMemoryToDoRepository
import com.ktor_todo_app.plugins.repository.ToDoRepositoryPattern
import com.ktor_todo_app.plugins.utils.respondTodos
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.callloging.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.request.*
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
            // call.respond(repository.getAllToDos())
            call.respondTodos(repository = repository)
        }

        get(path = "/todos/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()
            if (id == null) {
                call.respond(HttpStatusCode.BadRequest, message = "ID must be an integer")
                return@get
            }

            val todo = repository.getSingleToDo(id = id)
            if (todo == null) {
                call.respond(HttpStatusCode.NotFound, message = "Todo w/ ID $id could not be found")
            } else {
                call.respond(todo)
            }
        }

        post(path = "/todos") {
            val todoDraft = call.receive<ToDoDraft>()
            val todo = repository.addToDo(todoDraft)
            call.respond(todo)
        }

        put(path = "/todos/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()
            val todoDraft = call.receive<ToDoDraft>()
            if (id == null) {
                call.respond(HttpStatusCode.BadRequest, message = "ID must be an integer")
                return@put
            }

            val updatedToDo = repository.updateToDo(id = id, draft = todoDraft)
            if (updatedToDo) {
                call.respond(HttpStatusCode.OK, message = "ToDo w/ ID $id has been updated")
            } else {
                call.respond(HttpStatusCode.NotFound, message = "ToDo w/ ID $id could not be found")
            }
        }

        delete(path = "/todos/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()
            if (id == null) {
                call.respond(HttpStatusCode.BadRequest, message = "ID must be an integer")
                return@delete
            }
            val deletedToDo = repository.deleteToDo(id = id)
            if (deletedToDo) {
                call.respond(HttpStatusCode.OK, message = "ToDo w/ ID $id has been deleted")
            } else {
                call.respond(HttpStatusCode.NotFound, message = "ToDo w/ ID $id could not be found") }
        }
    }
}
