package ru.chudakov.page

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.ui.WebDriverWait

class SlidesPage(driver: WebDriver, wait: WebDriverWait) : AbstractPage(driver, wait) {
    override val pageUrl = "https://www.google.com/intl/ru_ru/slides/about"

    @FindBy(xpath = "//*[@id=\"section-intro\"]/div[2]/div/div/a")
    lateinit var openPresentationButton: WebElement

    fun redirectToSignInPage(signInPageUrl: String) {
        openPresentationButton.click()
        wait
                .withMessage("Redirect to signIn page does not work")
                .until { it.currentUrl.startsWith(signInPageUrl) }
    }
}
