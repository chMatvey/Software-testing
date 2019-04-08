package ru.chudakov.page

import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.ui.WebDriverWait

abstract class AbstractPage(protected val driver: WebDriver, protected val wait: WebDriverWait) {
    abstract val pageUrl: String

    init {
        initElements()
    }

    private fun initElements() {
        PageFactory.initElements(driver, this)
    }

    fun open() {
        if (!driver.currentUrl.startsWith(pageUrl)) {
            driver.get(pageUrl)
        }
        wait.until { it.currentUrl.startsWith(pageUrl) }
    }

    fun checkUrl(url: String = pageUrl): Boolean {
        return driver.currentUrl.startsWith(url)
    }
}

