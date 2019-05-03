package ru.chudakov.page.menu.docs

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.ui.WebDriverWait

class DocsSlideMenu(driver: WebDriver, wait: WebDriverWait) : AbstractDocsMenu(driver, wait) {
    @FindBy(xpath = "//*[@id=\"punch-slide-menu\"]")
    override lateinit var docsMenuButton: WebElement

    @FindBy(xpath = "/html/body/div[40]")
    override lateinit var dropdown: WebElement

    fun createNewSlide() {
        clickDropdownButton("div[1]")
    }

    fun duplicateSlide() {
        clickDropdownButton("div[2]")
    }

    fun deleteSlide() {
        clickDropdownButton("div[3]")
    }

    fun missSlide() {
        clickDropdownButton("div[4]")
    }
}
