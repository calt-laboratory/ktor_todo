package com.ktor_todo_app.plugins.database

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column


object DBToDoTable : IntIdTable(name = "todo") {
    val title: Column<String> = varchar(name = "title", length = 255)
    val done: Column<Boolean> = bool(name = "done")
}

class DBToDoEntity(id: EntityID<Int>) : IntEntity(id) {

    companion object : IntEntityClass<DBToDoEntity>(DBToDoTable)

    var title by DBToDoTable.title
    var done by DBToDoTable.done
}
