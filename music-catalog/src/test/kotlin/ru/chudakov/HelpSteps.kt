package ru.chudakov

import cucumber.api.java8.En
import ru.chudakov.service.HelpService
import kotlin.test.assertEquals

class HelpSteps : En {
    private lateinit var helpService: HelpService

    init {
        Given("User run application") {
            helpService = HelpService()
        }

        Then("User get help") {
            assertEquals("Music Catalog", helpService.getHelp())
        }
    }
}
