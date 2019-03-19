package ru.chudakov.page

import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.ui.WebDriverWait

abstract class AbstractPage(private val driver: WebDriver) {
    abstract val pageUrl: String

    init {
        PageFactory.initElements(driver, this);
    }

    fun open() = driver.get(pageUrl)

    fun verifyUrl(currentUrl: String) {
        WebDriverWait(driver, 10)
    }
}
