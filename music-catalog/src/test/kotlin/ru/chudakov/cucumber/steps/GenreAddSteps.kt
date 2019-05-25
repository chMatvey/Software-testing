package ru.chudakov.cucumber.steps

import cucumber.api.java.After
import cucumber.api.java.Before
import cucumber.api.java8.En
import org.jetbrains.exposed.sql.transactions.transaction
import ru.chudakov.DBManager
import ru.chudakov.PgDBManager
import ru.chudakov.dao.Genre
import kotlin.test.assertEquals

class GenreAddSteps : En {
    private val dbManager: DBManager = PgDBManager()

    private lateinit var name: String

    private var genre: Genre? = null

    init {
        Given("we have new genre {string}") { name: String ->
            this.name = name
        }

        When("we try add this genre") {
            genre = dbManager.addGenre(name)
        }

        Then("new genre equal null {string}") { result: String ->
            assertEquals(result.toBoolean(), genre == null)
        }
    }

    @After("@addGenre")
    fun after() {
        transaction {
            genre?.delete()
        }
    }
}
