package ru.chudakov.dao

import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.*

class PlaylistDao(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<PlaylistDao>(Playlists)

    var name by Playlists.name

    var compositions by CompositionDao via CompositionsPlaylists
}
