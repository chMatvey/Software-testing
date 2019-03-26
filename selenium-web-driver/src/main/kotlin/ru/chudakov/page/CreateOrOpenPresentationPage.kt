package ru.chudakov.page

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy

class CreateOrOpenPresentationPage(private val driver: WebDriver) : AbstractPage(driver) {
    override val pageUrl = "https://docs.google.com/presentation/u/0/";

    @FindBy(xpath = "//*[@id=\":1e\"]/div[1]")
    lateinit var createEmptyPresentationButton: WebElement

    fun createEmptyPresentation(presentationPageUrl: String) {
        createEmptyPresentationButton.click()
        wait
                .withMessage("")
                .until { checkUrl(presentationPageUrl) }
    }
}
