package ru.chudakov.dao

import org.jetbrains.exposed.sql.Table

object CompositionsPlaylists : Table() {
    val composition = reference("composition_id", Compositions).primaryKey(0)

    val playlist = reference("playlist_id", Playlists).primaryKey(1)
}
