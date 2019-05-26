package ru.chudakov.dao

import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass

class GenreDao(id: EntityID<Int>) : IntEntity(id) {
    companion object: IntEntityClass<GenreDao>(Genres)

    var name by Genres.name
}
