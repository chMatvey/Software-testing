package ru.chudakov

import org.openqa.selenium.chrome.ChromeDriver
import ru.chudakov.page.MainPage
import ru.chudakov.page.SignInPage
import java.util.concurrent.TimeUnit

fun main() {
    System.setProperty("webdriver.chrome.driver", "E:\\chromedriver_win32\\chromedriver.exe")
    val driver = ChromeDriver()

    val mainPage = MainPage(driver)
    mainPage.run {
        open()
        openPresentationButtonClick()
    }
    Thread.sleep(5000)

    val signInPage = SignInPage(driver)
    signInPage.run {
        inputEmail()
        clickNextButton()
    }
    Thread.sleep(5000)
}
