package ru.chudakov.cucumber

import cucumber.api.CucumberOptions
import cucumber.api.java.Before
import cucumber.api.junit.Cucumber
import org.junit.runner.RunWith

@RunWith(Cucumber::class)
@CucumberOptions(
        features = ["src/test/resources/cucumber/features"]
)
class RunnerTest
