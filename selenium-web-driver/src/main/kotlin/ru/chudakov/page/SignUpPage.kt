package ru.chudakov.page

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import java.net.PasswordAuthentication
import javax.swing.AbstractButton

class SignUpPage(private val driver: WebDriver) : AbstractPage(driver) {
    override val pageUrl = "https://accounts.google.com/signup/v2/webcreateaccount"

    @FindBy(xpath = "//*[@id=\"firstName\"]")
    lateinit var firstNameInput: WebElement

    @FindBy(xpath = "//*[@id=\"lastName\"]")
    lateinit var lastNameInput: WebElement

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
}

