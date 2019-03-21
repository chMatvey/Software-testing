package ru.chudakov.page

import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.ui.WebDriverWait

abstract class AbstractPage(private val driver: WebDriver) {
    abstract val pageUrl: String

    init {
        initElements();
    }

    private fun initElements() {
        PageFactory.initElements(driver, this)
    }

    fun open() {
        if (!driver.currentUrl.startsWith(pageUrl)) {
            driver.get(pageUrl)
        }
    }

    fun verifyUrl(url: String = pageUrl): Boolean {
        return driver.currentUrl.startsWith(url)
    }
}
