package ru.chudakov.dao

import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass

class AuthorDao(id: EntityID<Int>) : IntEntity(id) {
    companion object: IntEntityClass<AuthorDao>(Authors)

    var name by Authors.name
}
