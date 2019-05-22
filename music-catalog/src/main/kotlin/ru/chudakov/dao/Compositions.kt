package ru.chudakov.dao

import org.jetbrains.exposed.dao.IntIdTable

object Compositions : IntIdTable() {
    val name = varchar("name", 50)

    val author = reference("author_id", Authors)

    val genre = reference("genre_id", Genres)

    init {
        index(true, name, author)
    }
}
