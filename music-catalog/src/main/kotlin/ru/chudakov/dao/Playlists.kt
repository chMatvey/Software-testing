package ru.chudakov.dao

import org.jetbrains.exposed.dao.IntIdTable

object Playlists : IntIdTable() {
    val name = varchar("name", 50).uniqueIndex()
}
