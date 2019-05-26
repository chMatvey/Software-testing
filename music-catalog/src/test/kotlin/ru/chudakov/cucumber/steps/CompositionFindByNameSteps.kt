package ru.chudakov.cucumber.steps

import cucumber.api.java8.En
import ru.chudakov.DBManager
import ru.chudakov.PgDBManager
import ru.chudakov.data.Composition
import kotlin.test.assertEquals

class CompositionFindByNameSteps : En {
    private val dbManager: DBManager = PgDBManager()

    private lateinit var compositionName: String
    private lateinit var authorName: String

    private var composition: Composition? = null

    init {
        Given("we have composition name {string}, {string}") { compositionName: String, authorName: String ->
            this.compositionName = compositionName
            this.authorName = authorName
        }

        When("we find composition by name") {
            composition = dbManager.findCompositionByName(compositionName, authorName)
        }

        Then("we get composition {string}") { result: String ->
            assertEquals(result.toBoolean(), composition != null)
        }
    }
}
