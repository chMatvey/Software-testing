package ru.chudakov.page.menu

import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.ui.WebDriverWait

abstract class AbstractMenu(protected val driver: WebDriver, protected val wait: WebDriverWait) {
    init {
        initElements()
    }

    private fun initElements() {
        PageFactory.initElements(driver, this)
    }

    protected open fun clickButton(buttonXpath: String, dialogXpath: String) {
        val button = driver.findElement(By.xpath(buttonXpath))
        button.click()

        val dialog = driver.findElement(By.xpath(dialogXpath))
        wait.until { dialog.isDisplayed }
    }
}
