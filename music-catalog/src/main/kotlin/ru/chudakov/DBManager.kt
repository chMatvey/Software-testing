package ru.chudakov

import ru.chudakov.data.Author
import ru.chudakov.data.Composition
import ru.chudakov.data.Genre
import ru.chudakov.data.Playlist

interface DBManager {

    fun addComposition(compositionName: String, authorName: String, genreName: String): Composition?

    fun deleteCompositions(compositionName: String, authorName: String)

    fun getAllCompositions(): List<Composition>

    fun addGenre(name: String): Genre?

    fun deleteGenres(name: String)

    fun getAllGenres(): List<Genre>

    fun findCompositionByName(compositionName: String, authorName: String): Composition?

    fun findCompositionsByAuthor(authorName: String): List<Composition>

    fun findCompositionsByGenre(genreName: String): List<Composition>

    fun getAllAuthors(): List<Author>

    fun createPlaylist(name: String): Playlist?

    fun deletePlaylist(name: String)

    fun getAllPlaylistNames(): List<String>

    fun getPlaylist(name: String): Playlist?

    fun addCompositionToPlaylist(playlistName: String, compositionName: String, authorName: String): Boolean

    fun deleteCompositionFromPlaylist(playlistName: String, compositionName: String, authorName: String): Boolean
}
