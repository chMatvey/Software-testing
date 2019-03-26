package ru.chudakov.page

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy

class SignUpPage(private val driver: WebDriver) : AbstractPage(driver) {
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
        nameInput.sendKeys(name)
        surnameInput.sendKeys(surname)
    }

    fun inputLatinCharsToUserNameInput() {
        userNameInput.clear()
        userNameInput.sendKeys("Пользователь")
        nextButton.click()
        wait
                .withMessage("")
                .until { userNameMustContainOnlyLatinCharsDiv.isDisplayed }
    }

    fun inputShortUserName() {
        userNameInput.clear()
        userNameInput.sendKeys("user")
        nextButton.click()
        wait
                .withMessage("")
                .until { userNameMustContainMinSixCharsDiv.isDisplayed }
    }

    fun inputShortPassword() {
        passwordInput.clear()
        confirmPasswordInput.clear()
        passwordInput.sendKeys("word")
        confirmPasswordInput.sendKeys("word")
        nextButton.click()
        wait
                .withMessage("")
                .until { passwordMustBeEightCharOrMoreDiv.isDisplayed }
    }

    fun inputNotEqualPasswords() {
        passwordInput.clear()
        confirmPasswordInput.clear()
        passwordInput.sendKeys("password1")
        confirmPasswordInput.sendKeys("password")
        nextButton.click()
        wait
                .withMessage("")
                .until { passwordMustBeMatchDiv.isDisplayed }
    }
}

