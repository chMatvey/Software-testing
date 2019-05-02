package ru.chudakov.page

import org.openqa.selenium.Alert
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import org.openqa.selenium.NoAlertPresentException



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

    fun reload() {
        if (driver.currentUrl.startsWith(pageUrl)) {
            driver.navigate().refresh()
        }

        closeAlert()

        wait.until { it.currentUrl.startsWith(pageUrl) }
        wait.until { driver.findElement(By.xpath("html")).isDisplayed }
    }

    fun checkUrl(url: String = pageUrl): Boolean {
        return driver.currentUrl.startsWith(url)
    }

    private fun closeAlert() {
        try {
            Thread.sleep(500)
            val alert = driver.switchTo().alert()
            alert.accept()
            driver.switchTo().defaultContent()
        }
        catch (e: NoAlertPresentException) {
        }
    }
}

