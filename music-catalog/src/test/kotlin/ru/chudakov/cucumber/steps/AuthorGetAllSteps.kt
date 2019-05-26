package ru.chudakov.cucumber.steps

import cucumber.api.java8.En
import ru.chudakov.DBManager
import ru.chudakov.PgDBManager
import ru.chudakov.data.Author
import kotlin.test.assertTrue

class AuthorGetAllSteps : En {
    private val dbManager: DBManager = PgDBManager()

    private var authors: List<Author> = emptyList()

    init {
        When("we try get all authors") {
            authors = dbManager.getAllAuthors()
        }

        Then("authors list is not empty") {
            assertTrue { authors.isNotEmpty() }
        }
    }
}
