package ru.chudakov

import io.kotlintest.specs.StringSpec
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.support.ui.WebDriverWait
import ru.chudakov.page.MainPage
import ru.chudakov.page.SignInPage
import java.util.concurrent.TimeUnit

class TestSignUp : StringSpec() {
    private val driver: WebDriver
    private val mainPage: MainPage
    private val signInPage: SignInPage

    init {
        System.setProperty("webdriver.chrome.driver", "C:\\project\\web-driver\\chromedriver.exe");
        driver = ChromeDriver()
        mainPage = MainPage(driver)
        signInPage = SignInPage(driver)
        driver.manage()?.timeouts()?.implicitlyWait(10, TimeUnit.SECONDS)
        driver.manage()?.window()?.maximize()

        "Страница открывается" {
            mainPage.run {
                open()
                openPresentationButtonClick()
                verifyUrl(mainPage.pageUrl)
            }
        }
    }
}
