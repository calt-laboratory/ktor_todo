package com.ktor_todo_app.plugins

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

        val todos = listOf(
            Todo(id = 2, title = "learn Kotlin", done = true),
            Todo(id = 3, title = "learn Python", done = true),
            Todo(id = 1, title = "learn ktor", done = false),
        )

        get(path = "/") {
            call.respondText("Todo List")
        }

        get(path = "/todos") {
            call.respond(todos)
        }

        get(path = "/todos/{id}") {
            val id = call.parameters["id"]
            call.respondText { "Todo item: $id"}
        }

        post(path = "/todos") {

        }

        put(path = "/todos/{id}") {

        }

        delete(path = "/todos/{id}") {

        }
    }
}
