package ru.chudakov.cucumber.steps

import cucumber.api.java8.En
import ru.chudakov.DBManager
import ru.chudakov.PgDBManager
import ru.chudakov.data.Composition
import kotlin.test.assertEquals

class CompositionFindByAuthorSteps : En {
    private val dbManager: DBManager = PgDBManager()

    private lateinit var name: String

    private lateinit var compositions: List<Composition>

    init {
        Given("we have author name {string}") { name: String ->
            this.name = name
        }

        When("we find compositions by author name") {
            compositions = dbManager.findCompositionsByAuthor(name)
        }

        Then("we get compositions list is not empty {string}") { result: String ->
            val actual = compositions.isNotEmpty()
            assertEquals(result.toBoolean(), actual)

            if (actual) {
                assertEquals(name, compositions.first().author.name)
            }
        }
    }
}
