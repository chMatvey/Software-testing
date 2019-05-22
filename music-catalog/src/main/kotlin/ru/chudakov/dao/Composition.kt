package ru.chudakov.dao

import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass

class Composition(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Composition>(Compositions)

    var name by Compositions.name

    var author by Author referencedOn Compositions.author

    var genre by Genre referencedOn Compositions.genre
}
