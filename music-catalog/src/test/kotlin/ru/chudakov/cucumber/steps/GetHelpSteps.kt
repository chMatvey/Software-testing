package ru.chudakov.cucumber.steps

import cucumber.api.java.Before
import cucumber.api.java8.En
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import ru.chudakov.ConsoleApplication
import ru.chudakov.DBManager
import kotlin.test.assertFalse

class GetHelpSteps : En {

    @MockK
    lateinit var dbManager: DBManager

    lateinit var consoleApplication: ConsoleApplication

    lateinit var result: String

    @Before("@getHelp")
    fun before() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        consoleApplication = ConsoleApplication(dbManager)
    }

    init {
        When("we try get help") {
            result = consoleApplication.getHelp()
        }

        Then("we get get information about using app") {
            assertFalse { result.isEmpty() }
        }
    }
}
