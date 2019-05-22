package ru.chudakov

import ru.chudakov.dao.Composition
import ru.chudakov.dao.Genre

interface DBManager {

    fun addComposition(compositionName: String, authorName: String, genreName: String): Composition?

    fun addGenre(name: String): Genre?
}
