package ru.chudakov.page

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import kotlin.coroutines.coroutineContext

class SignInPage(private val driver: WebDriver) : AbstractPage(driver) {
    override val pageUrl = "https://accounts.google.com/signin/v2/sl/pwd?service=wise&passive=1209600&continue=https%3A%2F%2Fdocs.google.com%2Fpresentation%2F%3Fusp%3Dmkt_slides"

    @FindBy(xpath = "//*[@id=\"identifierId\"]")
    lateinit var emailInput: WebElement

    @FindBy(css = "#password > div.aCsJod.oJeWuf > div > div.Xb9hP > input")
    lateinit var passwordInput: WebElement

    @FindBy(xpath = "//*[@id=\"identifierNext\"]")
    lateinit var nextEmailButton: WebElement

    @FindBy(xpath = "//*[@id=\"passwordNext\"]")
    lateinit var nextPasswordButton: WebElement

    @FindBy(css = "#ow241")
    lateinit var signUpButton: WebElement

    @FindBy(xpath = "//*[@id=\"initialView\"]/div[2]/div[3]/div")
    lateinit var createAccountForYouButton: WebElement

    @FindBy(xpath = "//*[@id=\"view_container\"]/div/div/div[2]/div/div[1]/div/form/content/section/div/content/div[1]/div/div[2]")
    lateinit var accountDoesNotExist: WebElement
}
