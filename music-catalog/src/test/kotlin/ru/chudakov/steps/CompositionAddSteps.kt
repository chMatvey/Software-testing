package ru.chudakov.steps

import cucumber.api.java8.En
import ru.chudakov.DBManager
import ru.chudakov.PgDBManager
import ru.chudakov.dao.Composition
import kotlin.test.assertEquals

class CompositionAddSteps : En {
    private val dbManager: DBManager = PgDBManager()

    private lateinit var compositionName: String
    private lateinit var authorName: String
    private lateinit var genreName: String

    private val compositions = mutableListOf<Composition>()
    private var lastComposition: Composition? = null

    init {
        Given("we have new composition {string}, {string} and {string}") { compositionName: String, authorName: String, genreName: String ->
            this.compositionName = compositionName
            this.authorName = authorName
            this.genreName = genreName
        }

        When("we try add this composition") {
            lastComposition = dbManager.addComposition(compositionName, authorName, genreName)
            lastComposition?.let { compositions.add(it) }
        }

        Then("new composition equal null {string}") { result: String ->
            assertEquals(result.toBoolean(), lastComposition == null)
        }
    }
}
