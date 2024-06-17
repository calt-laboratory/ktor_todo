package com.ktor_todo_app.plugins.database


import org.jetbrains.exposed.sql.Database


class DatabaseManager {

    // config
    private val hostname = "postgres-calt-laboratory"
    private val databaseName = "ktor_todo"
    private val userName = "ktor_todo"
    private val password = "ktor_todo"

    // database
    init {
        val dataBaseURL = "jdbc:postgresql://$hostname:3306/$databaseName"
        Database.connect(
            url = dataBaseURL,
            driver = "org.postgresql.Driver",
            user = userName,
            password = password
        )
    }
}
