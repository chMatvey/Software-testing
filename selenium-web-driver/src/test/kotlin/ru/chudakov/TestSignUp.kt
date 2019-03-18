package ru.chudakov

import io.kotlintest.matchers.string.shouldContain
import io.kotlintest.specs.StringSpec
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import java.util.concurrent.TimeUnit

class TestSignUp : StringSpec() {
    private val driver: WebDriver = ChromeDriver()
    private val signUpUrl = "https://account.mail.ru/signup"

    init {
        driver.manage()?.timeouts()?.implicitlyWait(10, TimeUnit.SECONDS)
        driver.manage()?.window()?.maximize()

        "Страница регистрации открывается" {
            driver.run {
                get(signUpUrl)
                pageSource.shouldContain("Регистрация")
                quit()
            }
        }
    }
}
