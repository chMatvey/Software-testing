package ru.chudakov.dao

import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass

class CompositionDao(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<CompositionDao>(Compositions)

    var name by Compositions.name

    var author by AuthorDao referencedOn Compositions.author

    var genre by GenreDao referencedOn Compositions.genre
}
