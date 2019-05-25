package ru.chudakov.cucumber.steps

import cucumber.api.java.Before
import cucumber.api.java8.En
import org.jetbrains.exposed.sql.transactions.transaction
import ru.chudakov.DBManager
import ru.chudakov.PgDBManager
import ru.chudakov.dao.Genre
import kotlin.test.assertFalse

class GenreDeleteSteps : En {
    private val dbManager: DBManager = PgDBManager()

    private lateinit var name: String

    private lateinit var genres: List<Genre>

    @Before("@genreDelete")
    fun before() {
        transaction {
            Genre.new { name = "name" }
        }
    }

    init {
        Given("we have genre {string}") { name: String ->
            this.name = name
        }

        When("we try delete genre") {
            dbManager.deleteGenres(name)
        }

        When("we get all genres") {
            genres = dbManager.getAllGenres()
        }

        Then("genres does not contains genre") {
            assertFalse { genres.any { it.name == name } }
        }
    }
}
