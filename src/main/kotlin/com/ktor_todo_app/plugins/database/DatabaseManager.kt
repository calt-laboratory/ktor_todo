package com.ktor_todo_app.plugins.database

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction

/**
 Establishes the connection to the database.
 */
class DatabaseManager {

    // config
    private val hostname = "localhost"
    private val databaseName = "ktor_todo"
    private val userName = "postgres"
    private val password = "password"

    // database
    private val exposedDatabase: Database by lazy {
        val dataBaseURL = "jdbc:postgresql://$hostname:5432/$databaseName"
        Database.connect(
            url = dataBaseURL,
            driver = "org.postgresql.Driver",
            user = userName,
            password = password
        )
    }

    fun  getAllTodos() : List<DBToDoEntity>{
        return transaction(exposedDatabase) {
            DBToDoEntity.all().toList()
        }
    }
}
