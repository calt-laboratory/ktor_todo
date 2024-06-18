package com.ktor_todo_app.plugins.database

import com.ktor_todo_app.plugins.entities.ToDoDraft
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
    private val port = 5432

    // database
    private val exposedDatabase: Database by lazy {
        val dataBaseURL = "jdbc:postgresql://$hostname:$port/$databaseName"
        Database.connect(
            url = dataBaseURL,
            driver = "org.postgresql.Driver",
            user = userName,
            password = password
        )
    }

    fun  getAllTodos() : List<DBToDoEntity>{
        return transaction(db = exposedDatabase) {
            DBToDoEntity.all().toList()
        }
    }

    fun getSingleToDo(id: Int): DBToDoEntity? {
        return transaction(db = exposedDatabase) {
            DBToDoEntity.findById(id = id)
        }
    }

    fun addToDo(toDoDraft: ToDoDraft) : DBToDoEntity {
        val newToDo = transaction(db = exposedDatabase) {
            DBToDoEntity.new {
                title = toDoDraft.title
                done = toDoDraft.done
            }
        }
        return newToDo
    }

    fun updateToDo(id: Int, toDoDraft: ToDoDraft) : Boolean {
        return transaction(db = exposedDatabase) {
            val toDo = DBToDoEntity.findById(id = id)
            if (toDo != null) {
                toDo.title = toDoDraft.title
                toDo.done = toDoDraft.done
                true
            } else {
                false
            }
        }
    }

    fun deleteToDo(id: Int) : Boolean {
        return transaction(db = exposedDatabase) {
            val todo = DBToDoEntity.findById(id = id)
            todo?.delete() != null
        }
    }
}
