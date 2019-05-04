package ru.chudakov.page.menu.docs

import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.ui.WebDriverWait

class DocsHelpMenu(driver: WebDriver, wait: WebDriverWait) : AbstractDocsMenu(driver, wait) {
    @FindBy(xpath = "//*[@id=\"docs-help-menu\"]")
    override lateinit var docsMenuButton: WebElement

    @FindBy(xpath = "/html/body/div[10]")
    override lateinit var dropdown: WebElement

    fun openHelp() {
        clickDropdownButton("div[3]")

        val dialogXpath = "//*[@id=\"google-feedback-wizard\"]"
        wait.until { driver.findElements(By.xpath(dialogXpath)).isNotEmpty() }

        action.sendKeys(Keys.ESCAPE).build().perform()
        Thread.sleep(500)
        action.sendKeys(Keys.ESCAPE).build().perform()
        Thread.sleep(500)
    }

    fun sendReviewOpen() {
        clickDropdownButton("div[7]")

        val dialogXpath = "//*[@id=\"google-feedback-wizard\"]"
        wait.until { driver.findElements(By.xpath(dialogXpath)).isNotEmpty() }

        action.sendKeys(Keys.ESCAPE).build().perform()
        Thread.sleep(500)
    }

    fun keyboardShortcutOpen() {
        clickDropdownButton("div[10]", "/html/body/div[@class='apps-shortcutshelppopup']")
    }
}
