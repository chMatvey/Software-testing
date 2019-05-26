package ru.chudakov.cucumber.steps

import cucumber.api.java.After
import cucumber.api.java8.En
import org.jetbrains.exposed.sql.transactions.transaction
import ru.chudakov.DBManager
import ru.chudakov.PgDBManager
import ru.chudakov.dao.*
import ru.chudakov.data.Composition
import kotlin.test.assertFalse

class CompositionDeleteSteps : En {
    private val dbManager: DBManager = PgDBManager()

    private lateinit var compositionName: String
    private lateinit var authorName: String

    private lateinit var compositions: List<Composition>

    init {
        Given("we have composition {string}, {string}") { compositionName: String, authorName: String ->
            this.compositionName = compositionName
            this.authorName = authorName
        }

        When("we try delete composition") {
            dbManager.deleteCompositions(compositionName, authorName)
        }

        When("we get all compositions") {
            compositions = dbManager.getAllCompositions()
        }

        Then("compositions does not contains composition") {
            assertFalse { compositions.any { it.name == compositionName && it.author.name == authorName } }
        }
    }

    @After("@compositionDelete")
    fun after() {
        transaction {
            AuthorDao.find { Authors.name eq "author" }.firstOrNull()?.delete()
        }
    }
}
