package ru.chudakov.dao

import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table

object CompositionsPlaylists : Table() {
    val composition =
            reference("composition_id", Compositions, ReferenceOption.CASCADE, ReferenceOption.CASCADE).primaryKey(0)

    val playlist =
            reference("playlist_id", Playlists, ReferenceOption.CASCADE, ReferenceOption.CASCADE).primaryKey(1)
}
