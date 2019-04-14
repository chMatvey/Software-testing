package ru.chudakov.page

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.ui.WebDriverWait

class CreatePresentationPage(driver: WebDriver, wait: WebDriverWait) : AbstractPage(driver, wait) {
    override val pageUrl = "https://docs.google.com/presentation/u/0/";

    @FindBy(xpath = "//*[@id=\":1e\"]/div[1]")
    lateinit var createEmptyPresentationButton: WebElement

    fun createEmptyPresentation() {
        createEmptyPresentationButton.click()
        wait
                .withMessage("")
                .until { checkUrl("https://docs.google.com/presentation/d") }
    }
}
