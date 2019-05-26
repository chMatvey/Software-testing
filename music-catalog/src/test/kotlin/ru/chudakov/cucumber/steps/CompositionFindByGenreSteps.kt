package ru.chudakov.cucumber.steps

import cucumber.api.java8.En
import ru.chudakov.DBManager
import ru.chudakov.PgDBManager
import ru.chudakov.data.Composition
import kotlin.test.assertEquals

class CompositionFindByGenreSteps : En {
    private val dbManager: DBManager = PgDBManager()

    private lateinit var name: String

    private lateinit var compositions: List<Composition>

    init {
        Given("we have genre name {string}") { name: String ->
            this.name = name
        }

        When("we find composition by genre name") {
            compositions = dbManager.findCompositionsByGenre(name)
        }

        Then("we get compositions list group by genre is not empty {string}") { result: String ->
            val actual = compositions.isNotEmpty()
            assertEquals(result.toBoolean(), actual)

            if (actual) {
                assertEquals(name, compositions.first().genre.name)
            }
        }
    }
}
