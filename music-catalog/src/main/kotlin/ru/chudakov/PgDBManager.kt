package ru.chudakov

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import ru.chudakov.dao.*
import java.lang.Exception
import java.lang.reflect.GenericArrayType
import java.sql.BatchUpdateException
import java.sql.SQLException

class PgDBManager : DBManager {
    private var message = ""

    init {
        Database.connect(PgDBConfig.url, PgDBConfig.driver, PgDBConfig.userName, PgDBConfig.password)

        transaction {
            addLogger()

            SchemaUtils.create(Authors)
            SchemaUtils.create(Genres)
            SchemaUtils.create(Compositions)

            if (Genre.all().empty()) {
                Genre.new { name = "pop" }
                Genre.new { name = "rap" }
                Genre.new { name = "classic" }
                Genre.new { name = "rock" }
            }
        }

        addComposition("name", "author", "pop")
    }

    override fun addComposition(compositionName: String, authorName: String, genreName: String): Composition? {
        var composition: Composition? = null

        transaction {
            val genre = Genre.find { Genres.name eq genreName }.firstOrNull()

            if (genre == null) {
                message = "This genre doesn't exist"
                return@transaction
            }

            val author = Author.find { Authors.name eq authorName }.firstOrNull() ?: Author.new { name = authorName }

            val isExist = Composition.find {
                Compositions.name eq compositionName
            }.any { it.author.id == author.id }

            if (isExist) {
                message = "This composition already exist"
                return@transaction
            }

            composition = Composition.new {
                name = compositionName
                this.author = author
                this.genre = genre
            }
        }

        return composition
    }

    override fun deleteCompositions(compositionName: String, authorName: String) {
        transaction {
            Composition.find { Compositions.name eq compositionName }
                    .firstOrNull { it.author.name == authorName }?.delete()
        }
    }

    override fun addGenre(name: String): Genre? {
        var result: Genre? = null

        transaction {
            val isExist = !Genre.find { Genres.name eq name }.empty()

            if (isExist) {
                message = "This genre already exist"
                return@transaction
            }

            result = Genre.new {
                this.name = name
            }
        }

        return result
    }

    override fun deleteGenres(name: String) {
        transaction {
            Genre.find { Genres.name eq name }.firstOrNull()?.delete()
        }
    }

    override fun getAllCompositions(): List<Composition> {
        return transaction {
            Composition.all().toList()
        }
    }

    override fun getAllGenres(): List<Genre> {
        return transaction {
            Genre.all().toList()
        }
    }

    override fun findCompositionByName(compositionName: String, authorName: String): Composition? {
        var result: Composition? = null

        transaction {
            val author = Author.find { Authors.name eq authorName }.firstOrNull() ?: return@transaction

            result = Composition.find {
                Compositions.name eq compositionName
            }.firstOrNull { it.author == author }
        }
        return result
    }

    override fun findCompositionsByAuthor(authorName: String): List<Composition> {
        var result = emptyList<Composition>()

        transaction {
            val authorId = Author.find { Authors.name eq authorName }.firstOrNull()?.id ?: return@transaction

            result = Composition.find {
                Compositions.author eq authorId
            }.toList()
        }
        return result
    }

    override fun findCompositionsByGenre(genreName: String): List<Composition> {
        var result = emptyList<Composition>()

        transaction {
            val genreId = Genre.find { Genres.name eq genreName}.firstOrNull()?.id ?: return@transaction

            result = Composition.find {
                Compositions.genre eq genreId
            }.toList()
        }
        return result
    }
}
