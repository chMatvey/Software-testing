package ru.chudakov.page

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy

class PresentationPage(private val driver: WebDriver) : AbstractPage(driver) {
    override val pageUrl = "https://docs.google.com/presentation/d";

    @FindBy(xpath = "//*[@id=\"docs-menubar\"]")
    lateinit var docsMenu: WebElement

    @FindBy(xpath = "//*[@id=\"newSlideButton\"]")
    lateinit var newSlideButton: WebElement

    @FindBy(xpath = "//*[@id=\"newSlideMenuButton\"]")
    lateinit var newSlideMenuButton: WebElement

    @FindBy(xpath = "//*[@id=\"undoButton\"]")
    lateinit var undoButton: WebElement

    @FindBy(xpath = "//*[@id=\"redoButton\"]")
    lateinit var redoButton: WebElement

    @FindBy(xpath = "//*[@id=\"printButton\"]")
    lateinit var printButton: WebElement

    @FindBy(xpath = "//*[@id=\"paintFormatButton\"]")
    lateinit var paintFormatButton: WebElement

    fun getDocsMenuButtons(): List<WebElement> {
        return driver.findElements(By.xpath("//*[@id=\"docs-menubar\"]/div"))
                .filter { webElement -> webElement.isEnabled && webElement.isDisplayed }
    }

    fun getDocsMenuSelects(): List<WebElement> {
        val selects = driver.findElements(By.xpath("/html/body/div"))
        System.out.println(selects[32].getAttribute("class"))
        System.out.println(selects[33].getAttribute("class"))
        System.out.println(selects[34].getAttribute("class"))
        return selects
    }

    fun clickDocsMenuButtons() {
        val buttons = getDocsMenuButtons()
        for (button in buttons) {
            button.click()
        }

        val selects = driver.findElements(
                By.xpath("//div[@class='goog-menu goog-menu-vertical docs-material docs-menu-hide-mnemonics']")
        )
        selects.add(driver.findElement(
                By.xpath("//div[@class='goog-menu goog-menu-vertical docs-material goog-menu-noaccel docs-menu-hide-mnemonics']"))
        )
        selects.add(driver.findElement(
                By.xpath("//div[@class='goog-menu goog-menu-vertical docs-material docs-omnibox-parent docs-menu-hide-mnemonics']"))
        )
        selects[1].click()
    }


}
