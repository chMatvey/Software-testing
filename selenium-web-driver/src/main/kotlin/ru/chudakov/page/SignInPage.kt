package ru.chudakov.page

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import kotlin.coroutines.coroutineContext

class SignInPage(private val driver: WebDriver) : AbstractPage(driver) {
    override val pageUrl = "https://accounts.google.com/signin/v2/identifier"

    @FindBy(xpath = "//*[@id=\"identifierId\"]")
    lateinit var emailInput: WebElement

    @FindBy(css = "#password > div.aCsJod.oJeWuf > div > div.Xb9hP > input")
    lateinit var passwordInput: WebElement

    @FindBy(xpath = "//*[@id=\"identifierNext\"]")
    lateinit var nextEmailButton: WebElement

    @FindBy(xpath = "//*[@id=\"passwordNext\"]")
    lateinit var nextPasswordButton: WebElement

    @FindBy(xpath = "/html/body/div[1]/div[1]/div[2]/div[2]/div/div/div[2]/div/div[2]/div/div[2]/div/div/content/span")
    lateinit var signUpButton: WebElement

    @FindBy(xpath = "//*[@id=\"initialView\"]/div[2]/div[3]/div")
    lateinit var createAccountForYourselfButton: WebElement

    @FindBy(xpath = "//*[@id=\"view_container\"]/div/div/div[2]/div/div[1]/div/form/content/section/div/content/div[1]/div/div[2]")
    lateinit var accountDoesNotExist: WebElement

    fun redirectToSignUpPage(signUpPageUrl: String) {
        signUpButton.click()
        wait
                .withMessage("signUpButton does not work")
                .until { createAccountForYourselfButton.isDisplayed }
        createAccountForYourselfButton.click()
        wait
                .withMessage("Redirect to signUp page does not work")
                .until { checkUrl(signUpPageUrl) }
    }

    fun signIn(resultPageUrl: String) {
        emailInput.sendKeys(System.getenv("google_login"))
        nextEmailButton.click()
        wait
                .withMessage("NextEmailButton does not work")
                .until { passwordInput.isDisplayed }

        passwordInput.sendKeys(System.getenv("google_password"))
        nextPasswordButton.click()
        wait
                .withMessage("NextPasswordButton does not work")
                .until { it.currentUrl.startsWith(resultPageUrl) }
    }
}
