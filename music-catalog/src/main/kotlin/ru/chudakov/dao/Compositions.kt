package ru.chudakov.dao

import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.ReferenceOption

object Compositions : IntIdTable() {
    val name = varchar("name", 50)

    val author = reference("author_id", Authors, ReferenceOption.CASCADE, ReferenceOption.CASCADE)

    val genre = reference("genre_id", Genres, ReferenceOption.CASCADE, ReferenceOption.CASCADE)

    init {
        index(true, name, author)
    }
}
