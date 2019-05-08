package ru.chudakov.page

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebDriverException
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.ui.WebDriverWait
import java.net.PasswordAuthentication
import kotlin.coroutines.coroutineContext

//Объектная модель страницы авторизации
class SignInPage(driver: WebDriver, wait: WebDriverWait) : AbstractPage(driver, wait) {
    override val pageUrl = "https://accounts.google.com/signin/v2/identifier?service=wise&passive=1209600&continue=https%3A%2F%2Fdocs.google.com%2Fpresentation%2F%3Fusp%3Dmkt_slides"

    //Текстовое поле для ввода адреса электронной почты
    //Метод initElements класса PageFactory, вызываемый в родительском конструкторе
    //инициализирует поле при первом обращеннии к нему
    //Аннотация FindBy указывает способ поиска элементанта на веб-странице
    @FindBy(xpath = "//*[@id=\"identifierId\"]")
    lateinit var emailInput: WebElement

    //Текстовое поле для ввода пароля
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
    lateinit var accountDoesNotExistDiv: WebElement

    @FindBy(xpath = "//*[@id=\"password\"]/div[2]/div[2]/div")
    lateinit var passwordIsNotRightDiv: WebElement

    //Переадресация на страницу регистрации
    fun redirectToSignUpPage(signUpPageUrl: String) {
        signUpButton.click()
        //Метод until ожидает выполнения переданного условия за время переданное в конструкторе класса WebDriverWait
        //Метод withMessage, в случае если веб-драйверу не удастся найти веб-элемент
        //за отведенное время, добавит в выброшенный Exception переданное сообщение
        wait.withMessage("signUpButton does not work").until { createAccountForYourselfButton.isDisplayed }
        createAccountForYourselfButton.click()
        try {
            val waitOneSecond = WebDriverWait(driver, 1)
            waitOneSecond.withMessage("Redirect to signUp page does not work").until { driver.currentUrl.startsWith(signUpPageUrl) }
        } catch(e: WebDriverException) {
            wait.withMessage("signUpButton does not work").until { createAccountForYourselfButton.isDisplayed }
            createAccountForYourselfButton.click()
            wait.withMessage("Redirect to signUp page does not work").until { driver.currentUrl.startsWith(signUpPageUrl) }
        }
    }

    fun inputRightLogin() {
        emailInput.clear()
        emailInput.sendKeys(System.getenv("google_login"))
        nextEmailButton.click()
        wait.withMessage("NextEmailButton does not work").until { passwordInput.isDisplayed }
    }

    fun inputRightPassword(resultPageUrl: String) {
        passwordInput.sendKeys(System.getenv("google_password"))
        nextPasswordButton.click()
        wait.withMessage("NextPasswordButton does not work").until { it.currentUrl.startsWith(resultPageUrl) }
    }

    fun inputUnExistLogin() {
        emailInput.sendKeys("mmmmmmmmmmmmmm")
        nextEmailButton.click()
        wait.until { accountDoesNotExistDiv.isDisplayed }
        emailInput.clear()
    }

    fun inputNotRightPassword() {
        passwordInput.sendKeys("password")
        nextPasswordButton.click()
        wait.until { passwordIsNotRightDiv.isDisplayed }
        passwordInput.clear()
    }
}
