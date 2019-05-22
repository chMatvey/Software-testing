package ru.chudakov.dao

import org.jetbrains.exposed.dao.IntIdTable

object Authors : IntIdTable() {
    val name = varchar("name", 50).uniqueIndex()
}
