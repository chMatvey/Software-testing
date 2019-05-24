package ru.chudakov

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction
import ru.chudakov.dao.*
import java.lang.Exception
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

                val pop = Genre.new { name = "pop" }
                val author = Author.new { name = "author" }
                Composition.new {
                    name = "name"
                    this.author = author
                    genre = pop
                }
            }
        }
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

    override fun deleteCompositions(composition: Composition) {
        transaction { composition.delete() }
    }

    override fun addGenre(name: String): Genre? {
        val result: Genre? = null

        transaction {
            val isExist = !Genre.find { Genres.name eq name }.empty()

            if (isExist) {
                message = "This genre already exist"
                return@transaction
            }

            Genre.new {
                this.name = name
            }
        }

        return result
    }

    override fun deleteGenres(genre: Genre) {
        transaction { genre.delete() }
    }
}
