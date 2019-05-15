package ru.chudakov

import cucumber.api.java8.En

class CompositionAddSteps : En {

    init {
        Given("we have composition list without composition {string}, {string} and {string}") { string: String, string2: String, string3: String ->
            // Write code here that turns the phrase above into concrete actions
            throw cucumber.api.PendingException()
        }

        When("we try add composition composition {string}, {string} and {string}") { string: String, string2: String, string3: String ->
            // Write code here that turns the phrase above into concrete actions
            throw cucumber.api.PendingException()
        }

        Then("we can find composition in db") {
            // Write code here that turns the phrase above into concrete actions
            throw cucumber.api.PendingException()
        }

        Given("we have composition list with composition {string}, {string} and {string}") { string: String, string2: String, string3: String ->
            // Write code here that turns the phrase above into concrete actions
            throw cucumber.api.PendingException()
        }

        When("we try add composition {string}, {string} and {string}") { string: String, string2: String, string3: String ->
            // Write code here that turns the phrase above into concrete actions
            throw cucumber.api.PendingException()
        }

        Then("we can error with message {string}") { string: String ->
            // Write code here that turns the phrase above into concrete actions
            throw cucumber.api.PendingException()
        }
    }
}
