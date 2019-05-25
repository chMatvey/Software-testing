package ru.chudakov

import ru.chudakov.dao.Composition
import ru.chudakov.dao.Genre

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
}
