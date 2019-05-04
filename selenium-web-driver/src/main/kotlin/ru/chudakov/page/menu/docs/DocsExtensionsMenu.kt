package ru.chudakov.page.menu.docs

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.ui.WebDriverWait

class DocsExtensionsMenu(driver: WebDriver, wait: WebDriverWait) : AbstractDocsMenu(driver, wait) {
    @FindBy(xpath = "//*[@id=\"docs-extensions-menu\"]")
    override lateinit var docsMenuButton: WebElement

    @FindBy(xpath = "/html/body/div[9]")
    override lateinit var dropdown: WebElement

    fun setExtensions() {
        clickDropdownButton("div[5]", "/html/body/div[@class='modal-dialog docs-dialog webstore-widget']")
    }
}
