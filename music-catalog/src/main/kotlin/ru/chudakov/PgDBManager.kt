package ru.chudakov

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction
import ru.chudakov.dao.*
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

//            val isExist = Composition.find {
//                Compositions.name eq compositionName
//            }.firstOrNull { it.author.id == author.id } != null
//
//            if (isExist) {
//                message = "This composition already exist"
//                return@transaction
//            }

            try {
                composition = Composition.new {
                    name = compositionName
                    this.author = author
                    this.genre = genre
                }
            } catch (e: SQLException) {
                rollback()
                message = "This composition already exist"
            }
        }

        return composition
    }

    override fun addGenre(name: String): Genre? {
        val result: Genre? = null

        transaction {

        }

        return result
    }
}
