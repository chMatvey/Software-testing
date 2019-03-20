package ru.chudakov.page

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory

class SignInPage(private val driver: WebDriver) : AbstractPage(driver) {
    override val pageUrl = "https://accounts.google.com/signin/v2/identifier"

    @FindBy(xpath = "//*[@id=\"identifierId\"]")
    lateinit var emailInput: WebElement

    @FindBy(xpath = "//*[@id=\"identifierNext\"]")
    lateinit var nextButton: WebElement

    fun inputEmail() {
        emailInput.sendKeys(System.getProperty("google_login"))
    }

    fun clickNextButton() {
        nextButton.click()
    }
}
