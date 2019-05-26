package ru.chudakov.cucumber.steps

import cucumber.api.java8.En
import ru.chudakov.DBManager
import ru.chudakov.PgDBManager
import ru.chudakov.data.Composition
import kotlin.test.assertTrue

class CompositionGetAllSteps : En {
    private val dbManager: DBManager = PgDBManager()

    private lateinit var compositions: List<Composition>

    init {
        When("we try get all composition") {
            compositions = dbManager.getAllCompositions()
        }

        Then("compositions list is not empty") {
            assertTrue { compositions.isNotEmpty() }
        }
    }
}
