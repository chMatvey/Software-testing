package ru.chudakov.cucumber.steps

import cucumber.api.java8.En
import ru.chudakov.DBManager
import ru.chudakov.PgDBManager
import ru.chudakov.dao.Genre
import kotlin.test.assertTrue

class GenreGetAllSteps: En {
    private val dbManager: DBManager = PgDBManager()

    private lateinit var genres: List<Genre>

    init {
        When("we try get all genres") {
            genres = dbManager.getAllGenres()
        }

        Then("genres list is not empty") {
            assertTrue { genres.isNotEmpty() }
        }
    }
}
