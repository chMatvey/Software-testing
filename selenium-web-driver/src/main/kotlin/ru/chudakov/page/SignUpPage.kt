package ru.chudakov.page

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.ui.ExpectedCondition
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.util.function.Function

class SignUpPage(private val driver: WebDriver, private val wait: WebDriverWait) :
        AbstractPage(driver, wait) {
    override val pageUrl = "https://accounts.google.com/signup/v2/webcreateaccount"

    @FindBy(xpath = "//*[@id=\"firstName\"]")
    lateinit var nameInput: WebElement

    @FindBy(xpath = "//*[@id=\"lastName\"]")
    lateinit var surnameInput: WebElement

    @FindBy(xpath = "//*[@id=\"username\"]")
    lateinit var userNameInput: WebElement

    @FindBy(xpath = "//*[@id=\"passwd\"]/div[1]/div/div[1]/input")
    lateinit var passwordInput: WebElement

    @FindBy(xpath = "//*[@id=\"confirm-passwd\"]/div[1]/div/div[1]/input")
    lateinit var confirmPasswordInput: WebElement

    @FindBy(xpath = "//*[@id=\"accountDetailsNext\"]")
    lateinit var nextButton: WebElement

    @FindBy(xpath = "//*[@id=\"view_container\"]/form/div[2]/div/div[2]/div[2]/div")
    lateinit var signInButton: WebElement

    @FindBy(xpath = "//*[@id=\"passwd\"]/div[2]/div[2]/div")
    lateinit var passwordMustBeEightCharOrMoreDiv: WebElement

    @FindBy(xpath = "//*[@id=\"confirm-passwd\"]/div[2]/div[2]/div")
    lateinit var passwordMustBeMatchDiv: WebElement

    @FindBy(xpath = "//*[@id=\"view_container\"]/form/div[2]/div/div[1]/div[2]/div[1]/div/div[2]/div[1]")
    lateinit var userNameMustContainOnlyLatinCharsDiv: WebElement

    @FindBy(xpath = "//*[@id=\"view_container\"]/form/div[2]/div/div[1]/div[2]/div[1]/div/div[2]/div[2]/div")
    lateinit var userNameMustContainMinSixCharsDiv: WebElement

    fun inputNameAndSurname(name: String = "name", surname: String = "surname") {
        nameInput.clear()
        surnameInput.clear()
        nameInput.sendKeys(name)
        surnameInput.sendKeys(surname)
    }

    fun inputLatinCharsToUserNameInput() {
        userNameInput.clear()
        userNameInput.sendKeys("Пользователь")
    }

    fun inputShortUserName() {
        userNameInput.clear()
        userNameInput.sendKeys("user")
        Thread.sleep(1000)
    }

    fun inputPassword(password: String = "Pasword12345QwertY") {
        passwordInput.clear()
        confirmPasswordInput.clear()
        passwordInput.sendKeys(password)
        confirmPasswordInput.sendKeys(password)
    }

    fun inputShortPassword() {
        passwordInput.clear()
        confirmPasswordInput.clear()
        passwordInput.sendKeys("word")
        confirmPasswordInput.sendKeys("word")
    }

    fun inputNotEqualPasswords() {
        passwordInput.clear()
        confirmPasswordInput.clear()
        passwordInput.sendKeys("password1")
        confirmPasswordInput.sendKeys("password")
    }

    fun clickNextButton(condition: ExpectedCondition<List<WebElement>>) {
        wait.until(ExpectedConditions.visibilityOf(nextButton))
        nextButton.click()
        wait.until(condition)
    }
}

